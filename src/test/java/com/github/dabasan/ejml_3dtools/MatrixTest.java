package com.github.dabasan.ejml_3dtools;

import static org.junit.Assert.*;

import java.util.Random;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

/**
 * Test class for Matrix
 * 
 * @author Daba
 *
 */
public class MatrixTest {
	@Test
	public void testConstructor() {
		var mat = new Matrix();
		// System.out.println(mat);
	}
	@Test
	public void testConstructor_SpecifyElements() {
		var mat = new Matrix(1.0);
		// System.out.println(mat);
	}
	@Test
	public void testConstructor_Matrix() {
		var mat1 = Matrix.createRandomMatrix();
		var mat2 = new Matrix(mat1);

		// System.out.println(mat1);
		// System.out.println(mat2);
	}
	@Test
	public void testConstructor_SimpleMatrix() {
		var sm = new SimpleMatrix(4, 4);

		var random = new Random();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sm.set(i, j, random.nextDouble());
			}
		}

		var mat = new Matrix(sm);
		// System.out.println(mat);
	}

	@Test
	public void testToArray() {
		var mat = Matrix.createRandomMatrix();
		double[] arr = mat.toArray();

		// System.out.println(mat);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// System.out.print(arr[i * 4 + j]);
				// System.out.print(" ");
			}
			// System.out.println();
		}
	}
	@Test
	public void testToArray2() {
		var mat = Matrix.createRandomMatrix();
		double[][] arr = mat.toArray2();

		// System.out.println(mat);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// System.out.print(arr[i][j]);
				// System.out.print(" ");
			}
			// System.out.println();
		}
	}

	@Test
	public void testAdd() {
		var mat1 = Matrix.createRandomMatrix();
		var mat2 = Matrix.createRandomMatrix();

		var add = mat1.add(mat2);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mat1.get(i, j) + mat2.get(i, j), add.get(i, j), 1.0E-6);
			}
		}
	}
	@Test
	public void testSub() {
		var mat1 = Matrix.createRandomMatrix();
		var mat2 = Matrix.createRandomMatrix();

		var sub = mat1.sub(mat2);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mat1.get(i, j) - mat2.get(i, j), sub.get(i, j), 1.0E-6);
			}
		}
	}
	@Test
	public void testMult() {
		var mat1 = Matrix.createRandomMatrix();
		var mat2 = Matrix.createRandomMatrix();

		var mult = mat1.mult(mat2);

		// System.out.println(mat1);
		// System.out.println(mat2);
		// System.out.println(mult);
	}

	@Test
	public void testTranspose() {
		var mat = Matrix.createRandomMatrix();
		var transposed = mat.transpose();

		// System.out.println(mat);
		// System.out.println(transposed);
	}
	@Test
	public void testInvert() {
		var mat = Matrix.createRandomMatrix();
		var inverse = mat.invert();

		// System.out.println(mat);
		// System.out.println(inverse);
	}

	@Test
	public void testCreateIdentityMatrix() {
		var mat = Matrix.createIdentityMatrix();
		// System.out.println(mat);
	}
	@Test
	public void testCreateRandomMatrix() {
		var mat = Matrix.createRandomMatrix();
		// System.out.println(mat);
	}
	@Test
	public void testCreateTranslationMatrix() {
		var mat = Matrix.createTranslationMatrix(1.0, -2.5, 4.0);
		// System.out.println(mat);
	}
	@Test
	public void testCreateScalingMatrix() {
		var mat = Matrix.createScalingMatrix(2.0, -3.0, 4.0);
		// System.out.println(mat);
	}
	@Test
	public void testCreateRotationXMatrix() {
		var mat = Matrix.createRotationXMatrix(Math.PI / 4.0);
		// System.out.println(mat);
	}
	@Test
	public void testCreateRotationYMatrix() {
		var mat = Matrix.createRotationYMatrix(Math.PI / 4.0);
		// System.out.println(mat);
	}
	@Test
	public void testCreateRotationZMatrix() {
		var mat = Matrix.createRotationZMatrix(Math.PI / 4.0);
		// System.out.println(mat);
	}
	@Test
	public void testCreateRotationMatrix() {
		var mat = Matrix.createRotationMatrix(1.0, 1.0, 1.0, Math.PI / 4.0);
		// System.out.println(mat);
	}
}
