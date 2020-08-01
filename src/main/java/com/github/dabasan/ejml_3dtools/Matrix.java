package com.github.dabasan.ejml_3dtools;

import java.util.Random;

import org.ejml.simple.SimpleMatrix;

/**
 * 4x4 matrix
 * 
 * @author Daba
 *
 */
public class Matrix {
	private SimpleMatrix m;

	/**
	 * All elements are set to 0.
	 */
	public Matrix() {
		m = new SimpleMatrix(4, 4);
	}
	/**
	 * All elements are set to the value specified.
	 * 
	 * @param value
	 *            Value
	 */
	public Matrix(double value) {
		m = new SimpleMatrix(4, 4);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				m.set(i, j, value);
			}
		}
	}
	/**
	 * Creates a matrix from a Matrix instance.
	 * 
	 * @param mat
	 *            Matrix instance
	 */
	public Matrix(Matrix mat) {
		m = new SimpleMatrix(4, 4);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				m.set(i, j, mat.get(i, j));
			}
		}
	}
	/**
	 * Creates a matrix from a SimpleMatrix instance.<br>
	 * Dimension of the input matrix must be 4x4.<br>
	 * All elements are set to 0 in case input dimension is invalid.
	 * 
	 * @param sm
	 *            Instance of SimpleMatrix
	 */
	public Matrix(SimpleMatrix sm) {
		m = new SimpleMatrix(4, 4);

		if (sm.numRows() == 4 && sm.numCols() == 4) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					m.set(i, j, sm.get(i, j));
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					m.set(i, j, 0.0);
				}
			}
		}
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sb.append(this.m.get(i, j));
				sb.append(" ");
			}
			sb.append("\n");
		}

		sb.setLength(sb.length() - 1);

		return sb.toString();
	}

	/**
	 * Returns the underlying SimpleMatrix instance.
	 * 
	 * @return SimpleMatrix instance
	 */
	public SimpleMatrix getSM() {
		return m;
	}

	public double get(int row, int col) {
		return m.get(row, col);
	}
	public float getFloat(int row, int col) {
		return (float) m.get(row, col);
	}

	public void set(int row, int col, double value) {
		m.set(row, col, value);
	}

	/**
	 * Returns an array containing all of the elements in this matrix.
	 * 
	 * @return Array
	 */
	public double[] toArray() {
		var ret = new double[16];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ret[i * 4 + j] = m.get(i, j);
			}
		}

		return ret;
	}
	/**
	 * Returns a 2-dimensional array containing all of the elements in this
	 * matrix.
	 * 
	 * @return Array
	 */
	public double[][] toArray2() {
		var ret = new double[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ret[i][j] = m.get(i, j);
			}
		}

		return ret;
	}

	/**
	 * Addition
	 * 
	 * @param mat
	 *            Matrix
	 * @return Matrix
	 */
	public Matrix add(Matrix mat) {
		var ret = new Matrix();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double v1 = this.m.get(i, j);
				double v2 = mat.m.get(i, j);

				ret.set(i, j, v1 + v2);
			}
		}

		return ret;
	}
	/**
	 * Subtraction
	 * 
	 * @param mat
	 *            Matrix
	 * @return Matrix
	 */
	public Matrix sub(Matrix mat) {
		var ret = new Matrix();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double v1 = this.m.get(i, j);
				double v2 = mat.m.get(i, j);

				ret.set(i, j, v1 - v2);
			}
		}

		return ret;
	}
	/**
	 * Multiplication
	 * 
	 * @param mat
	 *            Matrix
	 * @return Multiplied matrix
	 */
	public Matrix mult(Matrix mat) {
		SimpleMatrix multSM = this.m.mult(mat.m);
		return new Matrix(multSM);
	}

	/**
	 * Transposes this matrix.
	 * 
	 * @return Transposed matrix
	 */
	public Matrix transpose() {
		var ret = new Matrix();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ret.set(i, j, m.get(j, i));
			}
		}

		return ret;
	}
	/**
	 * Inverts this matrix.
	 * 
	 * @return Inverse matrix
	 */
	public Matrix invert() {
		return new Matrix(m.invert());
	}

	/**
	 * Creates an identity matrix.
	 * 
	 * @return Identity matrix
	 */
	public static Matrix createIdentityMatrix() {
		var ret = new Matrix(0.0);
		for (int i = 0; i < 4; i++) {
			ret.set(i, i, 1.0);
		}

		return ret;
	}
	/**
	 * Creates a matrix with random elements in it.<br>
	 * Range of each element is between 0.0 and 1.0.
	 * 
	 * @return Random matrix
	 */
	public static Matrix createRandomMatrix() {
		var ret = new Matrix(0.0);
		var random = new Random();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double value = random.nextDouble();
				ret.set(i, j, value);
			}
		}

		return ret;
	}
	/**
	 * Creates a translation matrix.
	 * 
	 * @param translationX
	 *            Translation X
	 * @param translationY
	 *            Translation Y
	 * @param translationZ
	 *            Translation Z
	 * @return Translation matrix
	 */
	public static Matrix createTranslationMatrix(double translationX, double translationY,
			double translationZ) {
		var ret = new Matrix(0.0);
		ret.set(0, 0, 1.0);
		ret.set(0, 3, translationX);
		ret.set(1, 1, 1.0);
		ret.set(1, 3, translationY);
		ret.set(2, 2, 1.0);
		ret.set(2, 3, translationY);
		ret.set(3, 3, 1.0);

		return ret;
	}
	/**
	 * Creates a scaling matrix.
	 * 
	 * @param scaleX
	 *            Scale X
	 * @param scaleY
	 *            Scale Y
	 * @param scaleZ
	 *            Scale Z
	 * @return Scaling matrix
	 */
	public static Matrix createScalingMatrix(double scaleX, double scaleY, double scaleZ) {
		var ret = new Matrix(0.0);
		ret.set(0, 0, scaleX);
		ret.set(1, 1, scaleY);
		ret.set(2, 2, scaleZ);
		ret.set(3, 3, 1.0);

		return ret;
	}
	/**
	 * Creates a matrix for rotation around the X-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return Rotation matrix
	 */
	public static Matrix createRotationXMatrix(double th) {
		var ret = new Matrix(0.0);
		ret.set(0, 0, 1.0);
		ret.set(1, 1, Math.cos(th));
		ret.set(1, 2, -Math.sin(th));
		ret.set(2, 1, Math.sin(th));
		ret.set(2, 2, Math.cos(th));
		ret.set(3, 3, 1.0);

		return ret;
	}
	/**
	 * Creates a matrix for rotation around the Y-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return Rotation matrix
	 */
	public static Matrix createRotationYMatrix(double th) {
		var ret = new Matrix(0.0);
		ret.set(0, 0, Math.cos(th));
		ret.set(0, 2, Math.sin(th));
		ret.set(1, 1, 1.0);
		ret.set(2, 0, -Math.sin(th));
		ret.set(2, 2, Math.cos(th));
		ret.set(3, 3, 1.0);

		return ret;
	}
	/**
	 * Creates a matrix for rotation around the Z-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return Rotation matrix
	 */
	public static Matrix createRotationZMatrix(double th) {
		var ret = new Matrix(0.0);
		ret.set(0, 0, Math.cos(th));
		ret.set(0, 1, -Math.sin(th));
		ret.set(1, 0, Math.sin(th));
		ret.set(1, 1, Math.cos(th));
		ret.set(2, 2, 1.0);
		ret.set(3, 3, 1.0);

		return ret;
	}
	/**
	 * Creates a matrix for rotation around an arbitrary axis.
	 * 
	 * @param axisX
	 *            X-component of the axis
	 * @param axisY
	 *            Y-component of the axis
	 * @param axisZ
	 *            Z-component of the axis
	 * @param th
	 *            Rotation angle (radian)
	 * @return Rotation matrix
	 */
	public static Matrix createRotationMatrix(double axisX, double axisY, double axisZ, double th) {
		double cosTh = Math.cos(th);
		double sinTh = Math.sin(th);

		var ret = new Matrix(0.0);
		ret.set(0, 0, cosTh + axisX * axisX * (1.0f - cosTh));
		ret.set(0, 1, axisX * axisY * (1.0f - cosTh) - axisZ * sinTh);
		ret.set(0, 2, axisX * axisZ * (1.0f - cosTh) + axisY * sinTh);
		ret.set(0, 3, 0.0f);
		ret.set(1, 0, axisY * axisX * (1.0f - cosTh) + axisZ * sinTh);
		ret.set(1, 1, cosTh + axisY * axisY * (1.0f - cosTh));
		ret.set(1, 2, axisY * axisZ * (1.0f - cosTh) - axisX * sinTh);
		ret.set(1, 3, 0.0f);
		ret.set(2, 0, axisZ * axisX * (1.0f - cosTh) - axisY * sinTh);
		ret.set(2, 1, axisZ * axisY * (1.0f - cosTh) + axisX * sinTh);
		ret.set(2, 2, cosTh + axisZ * axisZ * (1.0f - cosTh));
		ret.set(2, 3, 0.0f);
		ret.set(3, 0, 0.0f);
		ret.set(3, 1, 0.0f);
		ret.set(3, 2, 0.0f);
		ret.set(3, 3, 1.0f);

		return ret;
	}
}
