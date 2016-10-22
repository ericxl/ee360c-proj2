/**
 * Created by Eric on 10/21/16.
 */

import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;

public class UnitTest {

    @Test
    public void testEdgeEquals1() {
        Edge e1 = new Edge(1,2,1);
        Edge e2 = new Edge(2,1,1);
        assertEquals(e1, e2);
    }

    @Test
    public void testEdgeEquals2() {
        Edge e1 = new Edge(1,2,1);
        Edge e2 = new Edge(1,2,1);
        assertEquals(e1, e2);
    }

    @Test
    public void testEdgeNotEquals1() {
        Edge e1 = new Edge(1,2,1);
        Edge e2 = new Edge(1,2,2);
        assertNotEquals(e1, e2);
    }

    @Test
    public void testEdgeNotEquals2() {
        Edge e1 = new Edge(1,2,1);
        Edge e2 = new Edge(2,2,1);
        assertNotEquals(e1, e2);
    }

    @Test
    public void testEdgeToString() {
        Edge e1 = new Edge(1,2,1);
        assertEquals("1 2 1", e1.toString());

        Edge e2 = new Edge(2,1,1);
        assertEquals("1 2 1", e2.toString());
    }

    @Test
    public void testSolveGraph0(){
        ArrayList<Edge>[] graph = new ArrayList[5];
        graph[0] = new ArrayList<>(Arrays.asList(new Edge(0,1,1)));
        graph[1] = new ArrayList<>(Arrays.asList(new Edge(0,1,1), new Edge(1,2,2)));
        graph[2] = new ArrayList<>(Arrays.asList(new Edge(1,2,2), new Edge(2,4,3)));
        graph[3] = new ArrayList<>(Arrays.asList(new Edge(3,4,4)));
        graph[4] = new ArrayList<>(Arrays.asList(new Edge(3,4,4), new Edge(2,4,3)));
        ArrayList<Edge> trace = Program2.solveGraph(graph, new int[]{0,3,0,4});
        assertEquals(4, trace.size());
    }

    @Test
    public void testSolveGraph1(){
        ArrayList<Edge>[] graph = new ArrayList[5];
        graph[0] = new ArrayList<>(Arrays.asList(new Edge(0,1,1)));
        graph[1] = new ArrayList<>(Arrays.asList(new Edge(0,1,1), new Edge(1,2,4)));
        graph[2] = new ArrayList<>(Arrays.asList(new Edge(1,2,4), new Edge(2,4,3)));
        graph[3] = new ArrayList<>(Arrays.asList(new Edge(3,4,4)));
        graph[4] = new ArrayList<>(Arrays.asList(new Edge(2,4,3), new Edge(3,4,4)));
        ArrayList<Edge> trace = Program2.solveGraph(graph, new int[]{0,3,0,4});
        assertEquals(null, trace);
    }


    @Test
    public void testSolveGraph2(){
        ArrayList<Edge>[] graph = new ArrayList[7];
        graph[0] = new ArrayList<>(Arrays.asList(new Edge(0,1,2), new Edge(0,3,1), new Edge(0,4,1), new Edge(0,5,0)));
        graph[1] = new ArrayList<>(Arrays.asList(new Edge(0,1,2), new Edge(1,3,4), new Edge(1,2,3)));
        graph[2] = new ArrayList<>(Arrays.asList(new Edge(1,2,3), new Edge(2,3,1)));
        graph[3] = new ArrayList<>(Arrays.asList(new Edge(0,3,1), new Edge(1,3,4), new Edge(3,4,2), new Edge(2,3,1), new Edge(3,6,5)));
        graph[4] = new ArrayList<>(Arrays.asList(new Edge(0,4,1), new Edge(3,4,2)));
        graph[5] = new ArrayList<>(Arrays.asList(new Edge(0,5,0), new Edge(5,6,0)));
        graph[6] = new ArrayList<>(Arrays.asList(new Edge(5,6,0), new Edge(6,3,5)));
        ArrayList<Edge> trace = Program2.solveGraph(graph, new int[]{0,2,0,1});
        assertEquals(2, trace.size());
    }
}
