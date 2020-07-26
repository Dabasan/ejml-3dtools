package com.github.dabasan.ejml_vectools;

import org.ejml.simple.SimpleMatrix;

/**
 * Vector
 * 
 * @author Daba
 *
 */
public class Vector {
	private SimpleMatrix mat;

	/**
	 * X, Y and Z elements are set to 0.<br>
	 * W element is set to 1.
	 */
	public Vector() {
		mat = new SimpleMatrix(4, 1);
		mat.set(3, 0, 1.0);
	}
	/**
	 * Each element is set to the specified value.<br>
	 * W element is set to 1.
	 * 
	 * @param x
	 *            X
	 * @param y
	 *            Y
	 * @param z
	 *            Z
	 */
	public Vector(double x, double y, double z) {
		mat = new SimpleMatrix(4, 1);
		mat.set(0, 0, x);
		mat.set(1, 0, y);
		mat.set(2, 0, z);
		mat.set(3, 0, 1.0);
	}
	/**
	 * Creates a normalized vector from its angles.
	 * 
	 * @param angleV
	 *            Vertical angle in radians
	 * @param angleH
	 *            Horizontal angle in radians
	 */
	public Vector(double angleV, double angleH) {
		mat = new SimpleMatrix(4, 1);

		double x = Math.cos(angleH);
		double y = Math.sin(angleV);
		double z = Math.sin(angleH);
		double size = Math.sqrt(x * x + y * y + z * z);

		mat.set(0, 0, x / size);
		mat.set(1, 0, y / size);
		mat.set(2, 0, z / size);
		mat.set(3, 0, 1.0);
	}
	/**
	 * Creates a vector from a SimpleMatrix instance.<br>
	 * Size of the input matrix has to be 3x1 or 4x1.<br>
	 * X, Y and Z elements are set to 0 in case the instance has an invalid
	 * dimension.
	 * 
	 * @param sm
	 *            SimpleMatrix instance
	 */
	public Vector(SimpleMatrix sm) {
		if ((sm.numRows() == 3 || sm.numRows() == 4) && sm.numCols() == 1) {
			mat.set(0, 0, sm.get(0, 0));
			mat.set(1, 0, sm.get(1, 0));
			mat.set(2, 0, sm.get(2, 0));
		} else {
			mat.set(0, 0, 0.0);
			mat.set(1, 0, 0.0);
			mat.set(2, 0, 0.0);
		}
		mat.set(3, 0, 1.0);
	}

	public double getX() {
		return mat.get(0, 0);
	}
	public double getY() {
		return mat.get(1, 0);
	}
	public double getZ() {
		return mat.get(2, 0);
	}
	public float getXFloat() {
		return (float) mat.get(0, 0);
	}
	public float getYFloat() {
		return (float) mat.get(1, 0);
	}
	public float getZFloat() {
		return (float) mat.get(2, 0);
	}

	public void set(double x, double y, double z) {
		mat.set(0, 0, x);
		mat.set(1, 0, y);
		mat.set(2, 0, z);
	}
	public void setX(double value) {
		mat.set(0, 0, value);
	}
	public void setY(double value) {
		mat.set(1, 0, value);
	}
	public void setZ(double value) {
		mat.set(2, 0, value);
	}

	/**
	 * Returns the squared size of this vector.
	 * 
	 * @return Squared size
	 */
	public double getSquareSize() {
		double x = mat.get(0, 0);
		double y = mat.get(1, 0);
		double z = mat.get(2, 0);

		return x * x + y * y + z * z;
	}
	/**
	 * Returns the size of this vector.
	 * 
	 * @return Size
	 */
	public double getSize() {
		double x = mat.get(0, 0);
		double y = mat.get(1, 0);
		double z = mat.get(2, 0);

		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Normalize
	 * 
	 * @return Normalized vector
	 */
	public Vector normalize() {
		double size = this.getSize();

		double x = mat.get(0, 0) / size;
		double y = mat.get(1, 0) / size;
		double z = mat.get(2, 0) / size;

		return new Vector(x, y, z);
	}

	/**
	 * Returns the vertical angle of this vector.
	 * 
	 * @return Vertical angle in radians
	 */
	public double angleV() {
		double sinTh = this.getY() / this.getSize();
		double th = Math.asin(sinTh);

		return th;
	}
	/**
	 * Returns the horizontal angle of this vector.
	 * 
	 * @return Horizontal angle in radians
	 */
	public double angleH() {
		var xzVec = new Vector(this.getX(), 0.0, this.getZ());

		double cosTh = this.getX() / xzVec.getSize();
		double th = Math.acos(cosTh);

		if (this.getZ() >= 0.0) {
			th *= (-1.0f);
		}

		return th;
	}

	/**
	 * Addition
	 * 
	 * @param v
	 *            Vector
	 * @return Vector
	 */
	public Vector add(Vector v) {
		double x = this.getX() + v.getX();
		double y = this.getY() + v.getY();
		double z = this.getZ() + v.getZ();

		return new Vector(x, y, z);
	}
	/**
	 * Subtraction
	 * 
	 * @param v
	 *            Vector
	 * @return Vector
	 */
	public Vector sub(Vector v) {
		double x = this.getX() - v.getX();
		double y = this.getY() - v.getY();
		double z = this.getZ() - v.getZ();

		return new Vector(x, y, z);
	}
	/**
	 * Cross product
	 * 
	 * @param v
	 *            Vector
	 * @return Vector
	 */
	public Vector cross(Vector v) {
		double a1 = this.getX();
		double a2 = this.getY();
		double a3 = this.getZ();
		double b1 = v.getX();
		double b2 = v.getY();
		double b3 = v.getZ();

		double x = a2 * b3 - b2 * a3;
		double y = a3 * b1 - b3 * a1;
		double z = a1 * b2 - b1 * a2;

		return new Vector(x, y, z);
	}
	/**
	 * Dot product
	 * 
	 * @param v
	 *            Vector
	 * @return Dot product (double value)
	 */
	public double dot(Vector v) {
		return this.mat.dot(v.mat);
	}

	/**
	 * Rotates this vector around the X axis.
	 * 
	 * @param th
	 *            Angle in radians
	 * @return Rotated vector
	 */
	public Vector rotX(double th) {
		double[][] elements = new double[4][4];
		elements[0][0] = 1.0;
		elements[0][1] = 0.0;
		elements[0][2] = 0.0;
		elements[0][3] = 0.0;
		elements[1][0] = 0.0;
		elements[1][1] = Math.cos(th);
		elements[1][2] = -Math.sin(th);
		elements[1][3] = 0.0;
		elements[2][0] = 0.0;
		elements[2][1] = Math.sin(th);
		elements[2][2] = Math.cos(th);
		elements[2][3] = 0.0;
		elements[3][0] = 0.0;
		elements[3][1] = 0.0;
		elements[3][2] = 0.0;
		elements[3][3] = 1.0;

		var rotMat = new SimpleMatrix(elements);
		var rotSM = rotMat.mult(this.mat);

		return new Vector(rotSM);
	}
	/**
	 * Rotates this vector around the Y axis.
	 * 
	 * @param th
	 *            Angle in radians
	 * @return Rotated vector
	 */
	public Vector rotY(double th) {
		double[][] elements = new double[4][4];
		elements[0][0] = Math.cos(th);
		elements[0][1] = 0.0;
		elements[0][2] = Math.sin(th);
		elements[0][3] = 0.0;
		elements[1][0] = 0.0;
		elements[1][1] = 1.0;
		elements[1][2] = 0.0;
		elements[1][3] = 0.0;
		elements[2][0] = -Math.sin(th);
		elements[2][1] = 0.0;
		elements[2][2] = Math.cos(th);
		elements[2][3] = 0.0;
		elements[3][0] = 0.0;
		elements[3][1] = 0.0;
		elements[3][2] = 0.0;
		elements[3][3] = 1.0;

		var rotMat = new SimpleMatrix(elements);
		var rotSM = rotMat.mult(this.mat);

		return new Vector(rotSM);
	}
	/**
	 * Rotates this vector around the Z axis.
	 * 
	 * @param th
	 *            Angle in radians
	 * @return Rotated vector
	 */
	public Vector rotZ(double th) {
		double[][] elements = new double[4][4];
		elements[0][0] = Math.cos(th);
		elements[0][1] = -Math.sin(th);
		elements[0][2] = 0.0;
		elements[0][3] = 0.0;
		elements[1][0] = Math.sin(th);
		elements[1][1] = Math.cos(th);
		elements[1][2] = 0.0;
		elements[1][3] = 0.0;
		elements[2][0] = 0.0;
		elements[2][1] = 0.0;
		elements[2][2] = 1.0;
		elements[2][3] = 0.0;
		elements[3][0] = 0.0;
		elements[3][1] = 0.0;
		elements[3][2] = 0.0;
		elements[3][3] = 1.0;

		var rotMat = new SimpleMatrix(elements);
		var rotSM = rotMat.mult(this.mat);

		return new Vector(rotSM);
	}
	/**
	 * Rotates this vector around an axis specified.
	 * 
	 * @param axis
	 *            Axis of rotation
	 * @param th
	 *            Angle in radians
	 * @return Rotated vector
	 */
	public Vector rotAroundAxis(Vector axis, float th) {
		double cosTh = Math.cos(th);
		double sinTh = Math.sin(th);
		double nx = axis.getX();
		double ny = axis.getY();
		double nz = axis.getZ();

		double[][] elements = new double[4][4];
		elements[0][0] = cosTh + nx * nx * (1.0 - cosTh);
		elements[0][1] = nx * ny * (1.0 - cosTh) - nz * sinTh;
		elements[0][2] = nx * nz * (1.0 - cosTh) + ny * sinTh;
		elements[0][3] = 0.0;
		elements[1][0] = ny * nx * (1.0 - cosTh) + nz * sinTh;
		elements[1][1] = cosTh + ny * ny * (1.0 - cosTh);
		elements[1][2] = ny * nz * (1.0 - cosTh) - nx * sinTh;
		elements[1][3] = 0.0;
		elements[2][0] = nz * nx * (1.0 - cosTh) - ny * sinTh;
		elements[2][1] = nz * ny * (1.0 - cosTh) + nx * sinTh;
		elements[2][2] = cosTh + nz * nz * (1.0 - cosTh);
		elements[2][3] = 0.0;
		elements[3][0] = 0.0;
		elements[3][1] = 0.0;
		elements[3][2] = 0.0;
		elements[3][3] = 1.0;

		var rotMat = new SimpleMatrix(elements);
		var rotSM = rotMat.mult(this.mat);

		return new Vector(rotSM);
	}

	/**
	 * Transforms a vector with a matrix.<br>
	 * Dimension of input has to be 4x4.<br>
	 * Returns null if input has an invalid dimension.
	 * 
	 * @param matrix
	 *            4x4 matrix
	 * @return Transformed vector
	 */
	public Vector transform(SimpleMatrix matrix) {
		if (!(matrix.numRows() == 4 && matrix.numCols() == 4)) {
			return null;
		}

		var transformedSM = matrix.mult(this.mat);

		return new Vector(transformedSM);
	}
	/**
	 * Applies scaling and rotation to this vector (no translation).<br>
	 * Dimension of input has to be 4x4.<br>
	 * Returns null if input has an invalid dimension.
	 * 
	 * @param matrix
	 *            4x4 matrix
	 * @return Transformed vector
	 */
	public Vector transformSR(SimpleMatrix matrix) {
		if (!(matrix.numRows() == 4 && matrix.numCols() == 4)) {
			return null;
		}

		var matTemp = new SimpleMatrix(this.mat);
		matTemp.set(3, 0, 0.0);

		var transformedSM = matrix.mult(matTemp);

		return new Vector(transformedSM);
	}
}
