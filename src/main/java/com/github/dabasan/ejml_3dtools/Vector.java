package com.github.dabasan.ejml_3dtools;

import org.ejml.simple.SimpleMatrix;

/**
 * Vector
 * 
 * @author Daba
 *
 */
public class Vector {
	private SimpleMatrix v;

	/**
	 * X, Y and Z elements are set to 0.<br>
	 * W element is set to 1.
	 */
	public Vector() {
		v = new SimpleMatrix(4, 1);
		v.set(3, 0, 1.0);
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
		v = new SimpleMatrix(4, 1);
		v.set(0, 0, x);
		v.set(1, 0, y);
		v.set(2, 0, z);
		v.set(3, 0, 1.0);
	}
	/**
	 * Creates a normalized vector from its angles.
	 * 
	 * @param angleV
	 *            Vertical angle (radian)
	 * @param angleH
	 *            Horizontal angle (radian)
	 */
	public Vector(double angleV, double angleH) {
		v = new SimpleMatrix(4, 1);

		double x = Math.cos(angleH);
		double y = Math.sin(angleV);
		double z = -Math.sin(angleH);
		double size = Math.sqrt(x * x + y * y + z * z);

		v.set(0, 0, x / size);
		v.set(1, 0, y / size);
		v.set(2, 0, z / size);
		v.set(3, 0, 1.0);
	}
	/**
	 * Creates a vector from a SimpleMatrix instance.<br>
	 * Size of the input matrix must be 3x1 or 4x1.<br>
	 * X, Y and Z elements are set to 0 in case the instance has an invalid
	 * dimension.
	 * 
	 * @param sm
	 *            SimpleMatrix instance
	 */
	public Vector(SimpleMatrix sm) {
		v = new SimpleMatrix(4, 1);

		if ((sm.numRows() == 3 || sm.numRows() == 4) && sm.numCols() == 1) {
			v.set(0, 0, sm.get(0, 0));
			v.set(1, 0, sm.get(1, 0));
			v.set(2, 0, sm.get(2, 0));
		} else {
			v.set(0, 0, 0.0);
			v.set(1, 0, 0.0);
			v.set(2, 0, 0.0);
		}
		v.set(3, 0, 1.0);
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();

		sb.append("(");
		sb.append(this.getX());
		sb.append(", ");
		sb.append(this.getY());
		sb.append(", ");
		sb.append(this.getZ());
		sb.append(")");

		return sb.toString();
	}

	public double getX() {
		return v.get(0, 0);
	}
	public double getY() {
		return v.get(1, 0);
	}
	public double getZ() {
		return v.get(2, 0);
	}
	public float getXFloat() {
		return (float) v.get(0, 0);
	}
	public float getYFloat() {
		return (float) v.get(1, 0);
	}
	public float getZFloat() {
		return (float) v.get(2, 0);
	}

	public void set(double x, double y, double z) {
		v.set(0, 0, x);
		v.set(1, 0, y);
		v.set(2, 0, z);
	}
	public void setX(double value) {
		v.set(0, 0, value);
	}
	public void setY(double value) {
		v.set(1, 0, value);
	}
	public void setZ(double value) {
		v.set(2, 0, value);
	}

	/**
	 * Returns the squared size of this vector.
	 * 
	 * @return Squared size
	 */
	public double getSquareSize() {
		double x = v.get(0, 0);
		double y = v.get(1, 0);
		double z = v.get(2, 0);

		return x * x + y * y + z * z;
	}
	/**
	 * Returns the size of this vector.
	 * 
	 * @return Size
	 */
	public double getSize() {
		double x = v.get(0, 0);
		double y = v.get(1, 0);
		double z = v.get(2, 0);

		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Normalize
	 * 
	 * @return Normalized vector
	 */
	public Vector normalize() {
		double size = this.getSize();

		double x = v.get(0, 0) / size;
		double y = v.get(1, 0) / size;
		double z = v.get(2, 0) / size;

		return new Vector(x, y, z);
	}

	/**
	 * Returns the vertical angle of this vector.
	 * 
	 * @return Vertical angle (radian)
	 */
	public double getAngleV() {
		var xzVec = new Vector(this.getX(), 0.0, this.getZ());

		double cosTh = this.dot(xzVec) / (this.getSize() * xzVec.getSize());
		double th = Math.acos(cosTh);

		return th;
	}
	/**
	 * Returns the horizontal angle of this vector.
	 * 
	 * @return Horizontal angle (radian)
	 */
	public double getAngleH() {
		var xAxis = new Vector(1.0, 0.0, 0.0);
		var xzVec = new Vector(this.getX(), 0.0, this.getZ());

		double cosTh = xzVec.dot(xAxis) / xzVec.getSize();
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
		return this.getX() * v.getX() + this.getY() * v.getY() + this.getZ() * v.getZ();
	}

	/**
	 * Transforms a vector with a matrix.
	 * 
	 * @param matrix
	 *            Matrix
	 * @return Transformed vector
	 */
	public Vector transform(Matrix matrix) {
		var transformedSM = matrix.getSM().mult(this.v);

		return new Vector(transformedSM);
	}
	/**
	 * Applies scaling and rotation to this vector (no translation).
	 * 
	 * @param matrix
	 *            Matrix
	 * @return Transformed vector
	 */
	public Vector transformSR(Matrix matrix) {
		var matTemp = new SimpleMatrix(this.v);
		matTemp.set(3, 0, 0.0);

		var transformedSM = matrix.getSM().mult(matTemp);

		return new Vector(transformedSM);
	}

	/**
	 * Rotates this vector around the X axis.
	 * 
	 * @param th
	 *            Angle (radian)
	 * @return Rotated vector
	 */
	public Vector rotX(double th) {
		var rotMat = Matrix.createRotationXMatrix(th);
		var rotSM = rotMat.getSM().mult(this.v);

		return new Vector(rotSM);
	}
	/**
	 * Rotates this vector around the Y axis.
	 * 
	 * @param th
	 *            Angle (radian)
	 * @return Rotated vector
	 */
	public Vector rotY(double th) {
		var rotMat = Matrix.createRotationYMatrix(th);
		var rotSM = rotMat.getSM().mult(this.v);

		return new Vector(rotSM);
	}
	/**
	 * Rotates this vector around the Z axis.
	 * 
	 * @param th
	 *            Angle (radian)
	 * @return Rotated vector
	 */
	public Vector rotZ(double th) {
		var rotMat = Matrix.createRotationZMatrix(th);
		var rotSM = rotMat.getSM().mult(this.v);

		return new Vector(rotSM);
	}
	/**
	 * Rotates this vector around an axis specified.
	 * 
	 * @param axisX
	 *            X-component of the axis
	 * @param axisY
	 *            Y-component of the axis
	 * @param axisZ
	 *            Z-component of the axis
	 * @param th
	 *            Rotation angle (radian)
	 * @return Rotated vector
	 */
	public Vector rotAroundAxis(double axisX, double axisY, double axisZ, double th) {
		var rotMat = Matrix.createRotationMatrix(axisX, axisY, axisZ, th);
		var rotSM = rotMat.getSM().mult(this.v);

		return new Vector(rotSM);
	}
}
