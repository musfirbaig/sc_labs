/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

	/*
	 *	Testing Strategy
	 * 
	 *	Partition Graph.add(vertex):
	 * 		vertex: exists, not exists
	 * 
	 * 	Partition Graph.remove(vertex):
	 * 		vertex: label exists, label doesn't exist
	 * 
	 * 	Partition Graph.set(source, target, weight):
	 * 		source: exists, not exists
	 * 		target: exists, not exists
	 * 		edge: exists, not exists
	 * 		weight: 0, > 0
	 * 
	 * 	Partition Graph.vertices():
	 * 		Graph: empty, non-empty
	 * 
	 *	Partition Graph.sources(vertex):
	 * 		vertex: not exists in graph, exists
	 * 		vertex sources: 0, > 0
	 * 
	 *	Partition Graph.targets(vertex):
	 * 		vertex: not exists in graph, exists
	 * 		vertex targets: 0, > 0
	 * 	
	 */ 

	/**
	 * Overridden by implementation-specific test classes.
	 * 
	 * @return a new empty graph of the particular implementation being tested
	 */
	public abstract Graph<String> emptyInstance();

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void testInitialVerticesEmpty() {
		// TODO you may use, change, or remove this test
		assertEquals("Expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
	}

	// TODO other tests for instance methods of Graph

	/** Covers: Vertex not in graph */
	@Test
	public void testVertexAddNotExists() {
		Graph<String> graph = emptyInstance();

		int InitialNumOfVertices = graph.vertices().size();
		String vertex = "Test Vertex";
		boolean vertexAdded = graph.add(vertex);
		int CurrentNumOfVertices = graph.vertices().size();

		assertTrue("Expected vertex to be added", vertexAdded);
		assertEquals("Expected vertices to increase by one", InitialNumOfVertices + 1, CurrentNumOfVertices);
	}

	/** Covers: Vertex in graph */
	@Test
	public void testVertexAddExists() {
		Graph<String> graph = emptyInstance();

		String vertex1 = "Test Vertex 1";
		String vertex2 = "Text Vertex 2";

		boolean vertex1Added = graph.add(vertex1);
		boolean vertex2Added = graph.add(vertex2);

		int InitialNumOfVertices = graph.vertices().size();

		boolean vertex1AddedAgain = graph.add(vertex1);
		int CurrentNumOfVertices = graph.vertices().size();

		assertTrue("Expected vertex1 to be added", vertex1Added);
		assertTrue("Expected vertex2 to be added", vertex2Added);
		assertFalse("Expected vertex1 not to be added", vertex1AddedAgain);
		assertEquals("Expected same number of vertices", InitialNumOfVertices, CurrentNumOfVertices);
	}

	/** Covers: Vertex not in graph */
	@Test
	public void testVertexRemoveNotExists() {
		Graph<String> graph = emptyInstance();

		int InitialNumVertices = graph.vertices().size();
		boolean vertexRemoved = graph.remove("vertex");
		int CurrentNumVertices = graph.vertices().size();

		assertFalse("Expected no effect on graph after remove", vertexRemoved);
		assertEquals("Expected same number of vertices", InitialNumVertices, CurrentNumVertices);
	}

	/** Covers: Vertex in graph */
	@Test
	public void testVertexRemoveExists() {
		Graph<String> graph = emptyInstance();

		String vertex1 = "vertex1";
		String vertex2 = "vertex2";

		graph.add(vertex1);
		graph.add(vertex2);

		int InitialNumOfVertices = graph.vertices().size();

		boolean vertex2Removed = graph.remove(vertex2);

		int CurrentNumOfVertices = graph.vertices().size();

		assertTrue("Expected vertex removed", vertex2Removed);
		assertEquals("Expected number of vertices reduced by 1", InitialNumOfVertices - 1, CurrentNumOfVertices);
		assertTrue("Expected correct vertex removed", graph.vertices().contains(vertex1));
		assertFalse("Expected correct vertex removed", graph.vertices().contains(vertex2));
	}

	/**
	 * Covers: 1. Source exists 2. Target exists 3. Edge exists 4. Weight Zero
	 */
	@Test
	public void testSetEdgeExistsWeightZero() {

		Graph<String> graph = emptyInstance();

		String source = "vertex1";
		String target = "vertex2";
		int weight = 1;
		graph.set(source, target, weight);

		int previousWeight = graph.set(source, target, 0);
		Map<String, Integer> targets = graph.targets(source);
		Map<String, Integer> sources = graph.sources(target);

		assertEquals("Expected previous weight to be initial weight", weight, previousWeight);
		assertFalse("Expected edge removed from graph", sources.containsKey(source));
		assertFalse("Expected edge removed from graph", targets.containsKey(target));

	}

	/** Graph.vertices(): covers empty graph */
	@Test
	public void testGetVerticesEmptyGraph() {
		Graph<String> graph = emptyInstance();

		assertTrue("Expected empty set", graph.vertices().isEmpty());
	}

	/** Graph.vertices(): covers non-empty graph */
	@Test
	public void testGetVerticesNonEmptyGraph() {
		Graph<String> graph = emptyInstance();
		Set<String> vertices = new HashSet<String>();

		String v1 = "vertex1";
		String v2 = "vertex2";

		graph.add(v1);
		graph.add(v2);
		vertices.add(v1);
		vertices.add(v2);

		assertTrue("Expected v1 and v2 in set", graph.vertices().containsAll(vertices));
	}

	/** Graph.sources(): covers vertex not in graph */
	@Test
	public void testGetSourcesVertexNotExists() {
		Graph<String> graph = emptyInstance();
		assertTrue("Expected empty set", graph.sources("vertex2").isEmpty());
	}

	/** Graph.sources(): covers vertex in graph, sources zero */
	@Test
	public void testGetSourcesVertexExistsSourcesZero() {
		Graph<String> graph = emptyInstance();
		graph.add("vertex1");

		assertTrue("Expected empty set", graph.sources("vertex1").isEmpty());
	}

	/** Graph.sources(): covers vertex in graph, source > 0 */
	@Test
	public void testGetSourcesVertexExistsSourcesPositive() {
		Graph<String> graph = emptyInstance();

		String v1 = "vertex1";
		String v2 = "vertex2";
		String v3 = "vertex3";
		int weight = 1;
		graph.set(v1, v2, weight);
		graph.set(v1, v3, weight);
		graph.set(v2, v3, weight);

		Map<String, Integer> sourcesV2 = graph.sources(v2);
		Map<String, Integer> sourcesV3 = graph.sources(v3);

		assertTrue("Expected v2 to have source v1", sourcesV2.containsKey(v1));
		assertTrue("Expected v3 to have source v1", sourcesV3.containsKey(v1));
		assertTrue("Expected v3 to have source v2", sourcesV3.containsKey(v2));
	}

	/** Graph.targets(): covers vertex not in graph */
	@Test
	public void testGetTargetsVertexNotExists() {
		Graph<String> graph = emptyInstance();

		assertTrue("Expected empty set", graph.targets("vertex2").isEmpty());
	}

	/** Graph.targets(): covers vertex in graph, targets 0 */
	@Test
	public void testGetTargetsVertexExistsTargetsZero() {
		Graph<String> graph = emptyInstance();
		graph.add("vertex1");

		assertTrue("Expected empty set", graph.targets("vertex1").isEmpty());
	}


}