/**
 * Location Object for Forge
 * @author Emily Marriott
 * 
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Project New Beginning
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pnb.utils.geometry;

public class Location {
	
	private int dim;
	private double x;
	private double y;
	private double z;
	
	public Location(int dim, double x, double y, double z) {
		this.dim = dim;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getDim() {
		return dim;
	}
	public void setDim(int dim) {
		this.dim = dim;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	
	public double accurateDistanceTo(Location loc) {
		//Slow due to square root and powering, but it's accurate.
		return Math.sqrt(Math.pow(this.x-loc.x, 2) + Math.pow(this.y-loc.y, 2) + Math.pow(this.z-loc.z, 2));
	}
	
	/**
	 * Quick and dirty calculation. DOES NOT RETURN AN ACCURATE DISTANCE.
	 * This is for finding the shortest distance only.
	 * @param loc
	 * @return
	 */
	public double quickDistanceTo(Location loc) {
		//Not accurate at all, but should hopefully work for quick and dirty comparisons to other distances calculated this way.
		return Math.pow(this.x - loc.x,2) + Math.pow(this.y - loc.y,2) + Math.pow(this.z - loc.z,2);
	}
	
	@Override
	public String toString() {
		return "Dimension: " + this.dim + ", (" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
}
