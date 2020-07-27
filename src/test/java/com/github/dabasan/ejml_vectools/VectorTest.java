package com.github.dabasan.ejml_vectools;

import static org.junit.Assert.*;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import com.github.dabasan.ejml_3dtools.Vector;

/**
 * Test class for Vector
 * 
 * @author Daba
 *
 */
public class VectorTest {
	@Test
	public void testConstructor() {
		double[] expected = new double[]{0.0, 0.0, 0.0};

		var vec = new Vector();
		double[] actual = new double[]{vec.getX(), vec.getY(), vec.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testConstructor_XYZ() {
		double[] expected = new double[]{3.0, 4.0, -5.0};

		var vec = new Vector(3.0, 4.0, -5.0);
		double[] actual = new double[]{vec.getX(), vec.getY(), vec.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testConstructor_Angles() {
		double[] expected = new double[]{0.5774, 0.5774, -0.5774};

		var vec = new Vector(Math.PI / 4.0, Math.PI / 4.0);
		double[] actual = new double[]{vec.getX(), vec.getY(), vec.getZ()};

		assertArrayEquals(expected, actual, 1.0E-3);
	}
	@Test
	public void testConstructor_SimpleMatrix() {
		double[] expected = new double[]{3.0, 4.0, -5.0};

		var mat = new SimpleMatrix(3, 1);
		mat.set(0, 0, 3.0);
		mat.set(1, 0, 4.0);
		mat.set(2, 0, -5.0);

		var vec = new Vector(mat);
		double[] actual = new double[]{vec.getX(), vec.getY(), vec.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testConstructor_SimpleMatrix_2() {
		double[] expected = new double[]{3.0, 4.0, -5.0};

		var mat = new SimpleMatrix(4, 1);
		mat.set(0, 0, 3.0);
		mat.set(1, 0, 4.0);
		mat.set(2, 0, -5.0);
		mat.set(3, 0, 1.0);

		var vec = new Vector(mat);
		double[] actual = new double[]{vec.getX(), vec.getY(), vec.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}

	@Test
	public void testToString() {
		System.out.println(new Vector(1.0, 2.0, 3.0));
	}

	@Test
	public void testGetSquareSize() {
		double expected = 3.0;

		var vec = new Vector(1.0, 1.0, 1.0);
		double actual = vec.getSquareSize();

		assertEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testGetSize() {
		double expected = Math.sqrt(3.0);

		var vec = new Vector(1.0, 1.0, 1.0);
		double actual = vec.getSize();

		assertEquals(expected, actual, 1.0E-6);
	}

	@Test
	public void testNormalize() {
		double[] expected = new double[]{0.57735, 0.57735, 0.57735};

		var vec = new Vector(1.0, 1.0, 1.0);
		var normalizedVec = vec.normalize();
		double[] actual = new double[]{normalizedVec.getX(), normalizedVec.getY(),
				normalizedVec.getZ()};

		assertArrayEquals(expected, actual, 1.0E-3);
	}

	@Test
	public void testAngleV() {
		double expected = 0.61548;

		var vec = new Vector(1.0, 1.0, 1.0);
		double actual = vec.getAngleV();

		assertEquals(expected, actual, 1.0E-3);
	}
	@Test
	public void testAngleH() {
		double expected = -Math.PI / 4.0;

		var vec = new Vector(1.0, 1.0, 1.0);
		double actual = vec.getAngleH();

		assertEquals(expected, actual, 1.0E-3);
	}

	@Test
	public void testAdd() {
		double[] expected = new double[]{5.0, 9.0, 1.0};

		var v1 = new Vector(1.0, 2.0, 3.0);
		var v2 = new Vector(4.0, 7.0, -2.0);
		var add = v1.add(v2);
		double[] actual = new double[]{add.getX(), add.getY(), add.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testSub() {
		double[] expected = new double[]{-3.0, -5.0, 5.0};

		var v1 = new Vector(1.0, 2.0, 3.0);
		var v2 = new Vector(4.0, 7.0, -2.0);
		var sub = v1.sub(v2);
		double[] actual = new double[]{sub.getX(), sub.getY(), sub.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testCross() {
		double[] expected = new double[]{13.0, -12.0, 9.0};

		var v1 = new Vector(3.0, 4.0, 1.0);
		var v2 = new Vector(3.0, 7.0, 5.0);
		var cross = v1.cross(v2);
		double[] actual = new double[]{cross.getX(), cross.getY(), cross.getZ()};

		assertArrayEquals(expected, actual, 1.0E-6);
	}
	@Test
	public void testDot() {
		double expected = 42.0;

		var v1 = new Vector(3.0, 4.0, 1.0);
		var v2 = new Vector(3.0, 7.0, 5.0);
		double actual = v1.dot(v2);

		assertEquals(expected, actual, 1.0E-6);
	}
}
