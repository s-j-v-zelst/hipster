/*
 * Copyright 2013 Centro de Investigación en Tecnoloxías da Información (CITIUS).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.usc.citius.lab.hipster.algorithm;

import edu.uci.ics.jung.graph.DirectedGraph;
import es.usc.citius.lab.hipster.algorithm.multiobjective.maze.Maze2D;
import es.usc.citius.lab.hipster.testutils.JungEdge;
import es.usc.citius.lab.hipster.testutils.JungUtils;
import es.usc.citius.lab.hipster.testutils.MazeSearch;
import es.usc.citius.lab.hipster.testutils.MazeUtils;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Executes tests over predefined maze strings, comparing the results between
 * Jung and AD* iterator.
 *
 * @author Adrián González Sieira <adrian.gonzalez@usc.es>
 * @author Pablo Rodríguez Mier <pablo.rodriguez.mier@usc.es>
 * @since 26/03/2013
 * @version 1.0
 */
public class AStarMazeTest {

    public AStarMazeTest() {
    }


    @Test
    public void AStar_Maze1() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze1());
        execute(maze, true);
    }

    @Test
    public void Dijkstra_Maze1() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze1());
        execute(maze, false);
    }

    @Test
    public void AStar_Maze2() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze2());
        execute(maze, true);
    }

    @Test
    public void Dijkstra_Maze2() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze2());
        execute(maze, false);
    }

    @Test
    public void AStar_Maze3() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze3());
        execute(maze, true);
    }

    @Test
    public void Dijkstra_Maze3() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze3());
        execute(maze, false);
    }

    @Test
    public void AStar_Maze4() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze4());
        execute(maze, true);
    }

    @Test
    public void Dijkstra_Maze4() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze4());
        execute(maze, false);
    }

    @Test
    public void AStar_Maze5() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze5());
        execute(maze, true);
    }

    @Test
    public void Dijkstra_Maze5() throws InterruptedException {
        Maze2D maze = new Maze2D(MazeSearch.getTestMaze5());
        execute(maze, false);
    }

    private void execute(Maze2D maze, boolean heuristic) throws InterruptedException {
        AStar<Point,Double> it = MazeUtils.astar(maze, heuristic);
        /*
        PriorityFibonacciQueue<HeuristicNode<Point, Double>> q = new PriorityFibonacciQueue<HeuristicNode<Point, Double>>(new HeuristicNodePriorityEvaluator<Point, Double>());
        q.offer(it.getQueue().poll());
        it.setQueue(q);*/

        DirectedGraph<Point, JungEdge<Point>> graph = JungUtils.create(maze);
        MazeSearch.Result resultJung = MazeSearch.executeJungSearch(graph, maze.getInitialLoc(), maze.getGoalLoc());
        MazeSearch.Result resultIterator = MazeSearch.executeIteratorSearch(it, maze.getGoalLoc());
        assertEquals(resultIterator.getCost(), resultJung.getCost(), 0.0000001);
    }
    
    
}
