package com.fdmgroup.FindIslands;

import java.util.ArrayList;

/**
 * FindIslands looks through a 2 dimensional matrix and finds the number of islands
 * A group of 1s forms an island
 * To change table please alter the dimensions in lines 15-16 and init() method in line 23
 * 
 * @author daniel.thor.gerena
 */
public class FindIslands {
	
	// dimensions of original table
	int n = 4;
	int m = 4;
	int[][] table = new int[n][m]; // original table
	int[][] checkTable = new int[n][m]; // auxiliary table for marking visited locations
	int counterIslandComparisons = 0; // counter for # of comparisons performed
	ArrayList<String> islands = new ArrayList<String>();

	// Initialise the table with values
	public void init() {
		table[0][0] = 1;
		table[0][2] = 1;
		table[0][3] = 1;
		table[1][1] = 1;
		table[1][3] = 1;
		table[2][3] = 1;
		table[3][0] = 1;
		table[3][3] = 1;
	}

	public void run() {
		// Initialise the table with values
		init();
		// traverse the table
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				counterIslandComparisons++;
				// if we found a 1 and we haven't marked that as visited
				// (checkTable) then we start discovering the island
				if (table[i][j] == 1) {
					if (checkTable[i][j] == 0) {
						islands.add("(" + i + "," + j + ")");
						checkTable[i][j] = 1;
						findIsland(i, j);
					}
				}
			}
		}

		// print results
		printTable();
		printIslands();
		System.out.println("Number of table locations: " + (n * m));
		System.out.println("Number of Comparisons performed O(m*n) (worst case (9*n*m)): " + counterIslandComparisons);
	}

	// start from a point and check the 8 neighbours for a 1
	public void findIsland(int i, int j) {
		checkNeighbor(i + 1, j);
		checkNeighbor(i, j + 1);
		checkNeighbor(i - 1, j);
		checkNeighbor(i, j - 1);
		checkNeighbor(i + 1, j + 1);
		checkNeighbor(i - 1, j + 1);
		checkNeighbor(i + 1, j - 1);
		checkNeighbor(i - 1, j - 1);
	}

	// if neighbour is 1, add it to the island, mark the point as visited and
	// call findIsland recursively
	public void checkNeighbor(int i, int j) {
		counterIslandComparisons++;
		if (i >= 0 && j >= 0 && i < table.length && j < table[0].length && table[i][j] == 1) {
			if (checkTable[i][j] == 0) {
				islands.set(islands.size() - 1, islands.get(islands.size() - 1) + ",(" + i + "," + j + ")");
				checkTable[i][j] = 1;
				findIsland(i, j);
			}
		}
	}

	// auxiliary method to print the table
	public void printTable() {
		System.out.println("\nTable:");
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j]);
			}
			System.out.println();
		}
	}

	// auxiliary method to print the islands
	public void printIslands() {
		System.out.println("\nIslands:");
		for (int i = 0; i < islands.size(); i++) {
			System.out.println("Island #" + i + ": " + islands.get(i));
		}
	}

	public static void main(String[] args) {
		FindIslands i = new FindIslands();
		i.run();
	}
}
