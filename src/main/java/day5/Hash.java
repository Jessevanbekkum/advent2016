package day5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.codec.binary.Hex;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class Hash {

    MessageDigest digest = MessageDigest.getInstance("MD5");

    public Hash() throws NoSuchAlgorithmException {
    }

    public String hash(String plain) {
        digest.reset();
        byte[] result = this.digest.digest(plain.getBytes());

        return String.valueOf(Hex.encodeHex(result));
    }

    public String loop(String door) {
        List<String> hashes = Lists.newArrayList();
        int start = 0;
        while (hashes.size() < 8) {
            String candidate = hash(door + start);
            if (candidate.startsWith("00000")) {
                hashes.add(candidate);
            }
            start++;
        }

        return hashes.stream()
                .map(s -> s.charAt(5))
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public String door2(String code) {
        int start = 0;
        char[] solution = new char[8];
        Arrays.fill(solution, 'x');
        int found = 0;
        while (found != 8) {
            String candidate = hash(code + start);
            if (candidate.startsWith("00000")) {
                int position = (int) candidate.charAt(5);
                if (48 <= position && position < 56 && solution[position - 48] == 'x') {
                    solution[position - 48] = candidate.charAt(6);
                    found++;
                    System.out.println(solution);
                }

            }
            start++;
        }
        return String.valueOf(solution);
    }

    public String door2Fast(String code) {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Orders-%d")
                .setDaemon(true)
                .build();

        BlockingQueue<Map<Integer, String>> queue = new LinkedBlockingQueue<>();
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService pool = Executors.newFixedThreadPool(8, threadFactory);

        for (int i = 0; i < 8; i++) {
            pool.submit(new HashWorker(counter, queue, code));
        }


        ExecutorService combService = Executors.newSingleThreadExecutor();
        Future<String> submit = combService.submit(new Combiner(queue));
        try {
            String s = submit.get();
            pool.shutdownNow();
            return s;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);

        }

    }

    public class Combiner implements Callable<String> {
        private final BlockingQueue<Map<Integer, String>> queue;

        Combiner(BlockingQueue<Map<Integer, String>> queue) {

            this.queue = queue;
        }

        @Override
        public String call() {
            try {
                List<SortedMap<Integer, Character>> solutions = Lists.newArrayList();
                for (int i = 0; i < 8; i++) {
                    solutions.add(new TreeMap<>());
                }
                boolean solved = false;
                while (!solved) {
                    Map<Integer, String> take = queue.take();
                    for (Map.Entry<Integer, String> entry : take.entrySet()) {
                        String candidate = entry.getValue();
                        int position = (int) candidate.charAt(5);
                        if (48 <= position && position < 56) {
                            solutions.get(position - 48).put(entry.getKey(), candidate.charAt(6));
                            if (solutions.stream().noneMatch(Map::isEmpty)) {
                                solved = true;
                            }
                        }
                    }
                }
                String s = solutions.stream().map(m -> m.get(m.firstKey())).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                        .toString();
                return s;
            } catch (InterruptedException e) {
                Thread.interrupted();
                throw new RuntimeException(e);
            }
        }
    }

    public class HashWorker implements Runnable {
        private final String code;
        private final Queue<Map<Integer, String>> queue;
        private final AtomicInteger counter;
        MessageDigest digest;

        private String hash(String plain) {
            digest.reset();
            byte[] result = this.digest.digest(plain.getBytes());

            return String.valueOf(Hex.encodeHex(result));
        }


        public HashWorker(AtomicInteger counter, Queue<Map<Integer, String>> queue, final String code) {
            this.queue = queue;
            this.counter = counter;
            this.code = code;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            while (true) {
                Map<Integer, String> result = new HashMap<>();
                int myStart = counter.getAndIncrement() * 10000;
                for (int i = myStart; i < myStart + 10000; i++) {
                    String hash = hash(code + i);
                    if (hash.startsWith("00000")) {
                        result.put(i, hash);
                    }
                }
                queue.add(result);
            }
        }
    }
}
