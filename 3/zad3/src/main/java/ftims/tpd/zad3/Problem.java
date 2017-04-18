package ftims.tpd.zad3;

import java.util.*;

public class Problem {

    public Path[] solve(int[][] coincidenceMatrix, int startingPoint) {
        int verticesNum = coincidenceMatrix.length;
        VerticeRecord[] verticeRecords = initializeSingleSource(verticesNum, startingPoint);

        PriorityQueue<VerticeRecord> Q = new PriorityQueue<>(Comparator.comparingInt(u -> u.dist));
        Collections.addAll(Q, verticeRecords);

        while (!Q.isEmpty()) {
            VerticeRecord u = Q.poll();


            for (int i = 0; i < verticesNum; i++) {
                if (coincidenceMatrix[u.verticeIdx][i] != 0) {
                    VerticeRecord v = verticeRecords[i];
                    int w = coincidenceMatrix[u.verticeIdx][i];

                    if (v.dist > u.dist + w) {
                        v.dist = u.dist + w;
                        v.prev = u.verticeIdx;

                        Q.remove(v);
                        Q.offer(v);
                    }
                }
            }
        }

        Path[] paths = new Path[verticesNum];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = recreatePath(verticeRecords, i);
        }

        return paths;
    }

    private Path recreatePath(VerticeRecord[] shortestPaths, int verticeIdx) {
        List<Integer> verticesOnPath = new ArrayList<>();

        VerticeRecord v = shortestPaths[verticeIdx];
        verticesOnPath.add(v.verticeIdx);

        while (v.prev != VerticeRecord.NIL) {
            v = shortestPaths[v.prev];
            verticesOnPath.add(v.verticeIdx);
        }
        Collections.reverse(verticesOnPath);

        int dist = shortestPaths[verticeIdx].dist;
        return new Path(verticesOnPath.stream().mapToInt(i -> i).toArray(), dist);
    }

    private VerticeRecord[] initializeSingleSource(int verticesNum, int startingPoint) {
        VerticeRecord[] shortestPaths = new VerticeRecord[verticesNum];
        for (int i = 0; i < verticesNum; i++) {
            shortestPaths[i] = new VerticeRecord(VerticeRecord.INF, VerticeRecord.NIL, i);
        }
        shortestPaths[startingPoint].dist = 0;
        return shortestPaths;
    }
}

class VerticeRecord {

    static final int NIL = -1;

    static final int INF = Integer.MAX_VALUE;

    int dist;

    int prev;

    int verticeIdx;

    VerticeRecord(int dist, int prev, int verticeIdx) {
        this.dist = dist;
        this.prev = prev;
        this.verticeIdx = verticeIdx;
    }
}
