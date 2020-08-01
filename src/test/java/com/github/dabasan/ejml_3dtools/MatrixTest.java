package com.github.dabasan.ejml_3dtools;

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
	public void testAdd() {
		var mat1 = Matrix.createRandomMatrix();
		var mat2 = Matrix.createRandomMatrix();

		var add = mat1.add(mat2);

		// System.out.println(mat1);
		// System.out.println(mat2);
		// System.out.println(add);
	}
	@Test
	public void testSub() {
		var mat1 = Matrix.createRandomMatrix();
		var mat2 = Matrix.createRandomMatrix();

		var sub = mat1.sub(mat2);

		// System.out.println(mat1);
		// System.out.println(mat2);
		// System.out.println(sub);
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

	// Todo: Conduct tests with OpenGL programs.
	/*
	@Test
	public void testCreateTranslationMatrix() {
		
	}
	@Test
	public void testCreateScalingMatrix() {
		
	}
	@Test
	public void testCreateRotationXMatrix() {
		
	}
	@Test
	public void testCreateRotationYMatrix() {
		
	}
	@Test
	public void testCreateRotationZMatrix() {
		
	}
	@Test
	public void testCreateRotationMatrix() {
		
	}
	*/
}
