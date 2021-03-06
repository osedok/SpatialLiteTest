/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 *
 * Copyright (C) 2001 Vivid Solutions
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * For more information, contact:
 *
 *     Vivid Solutions
 *     Suite #1A
 *     2328 Government Street
 *     Victoria BC  V8T 5G5
 *     Canada
 *
 *     (250)385-6040
 *     www.vividsolutions.com
 */
package com.vividsolutions.jts.android;

import com.vividsolutions.jts.android.geom.DrawableShape;
import com.vividsolutions.jts.android.geom.OvalShape;
import com.vividsolutions.jts.android.geom.PathShape;
import com.vividsolutions.jts.android.geom.RectShape;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * An interface for classes which create {@link Shape}s to represent 
 * {@link Point}
 * geometries. Java2D does not provide an actual point shape, so some other
 * shape must be used to render points (e.g. such as a Rectangle or Ellipse).
 * 
 * <p>Modified for Android use.</p>
 * 
 * @author Martin Davis
 * @author Andrea Antonello (www.hydrologis.com)
 */
public interface PointShapeFactory {
    /**
     * Creates a shape representing a {@link Point}.
     * 
     * @param point
     *          the location of the point
     * @return a shape
     */
    DrawableShape createPoint( PointF point );

    public static abstract class BasePointShapeFactory implements PointShapeFactory {
        /**
         * The default size of the shape
         */
        public static double DEFAULT_SIZE = 3.0;

        protected double size = DEFAULT_SIZE;

        /**
         * Creates a new factory for points with default size.
         * 
         */
        public BasePointShapeFactory() {
        }

        /**
         * Creates a factory for points of given size.
         * 
         * @param size
         *          the size of the points
         */
        public BasePointShapeFactory( double size ) {
            this.size = size;
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public abstract DrawableShape createPoint( PointF point );
    }

    public static class Square extends BasePointShapeFactory {
        /**
         * Creates a new factory for squares with default size.
         * 
         */
        public Square() {
            super();
        }

        /**
         * Creates a factory for squares of given size.
         * 
         * @param size
         *          the size of the points
         */
        public Square( double size ) {
            super(size);
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public DrawableShape createPoint( PointF point ) {
            float x = (float) (point.x - (size / 2));
            float y = (float) (point.y - (size / 2));
            RectShape pointMarker = new RectShape(x, y, (float) size, (float) size);
            return pointMarker;
        }
    }

    public static class Star extends BasePointShapeFactory {
        /**
         * Creates a new factory for points with default size.
         * 
         */
        public Star() {
            super();
        }

        /**
         * Creates a factory for points of given size.
         * 
         * @param size
         *          the size of the points
         */
        public Star( double size ) {
            super(size);
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public DrawableShape createPoint( PointF point ) {
            Path path = new Path();
            path.moveTo((float) point.x, (float) (point.y - size / 2));
            path.lineTo((float) (point.x + size * 1 / 8), (float) (point.y - size * 1 / 8));
            path.lineTo((float) (point.x + size / 2), (float) (point.y - size * 1 / 8));
            path.lineTo((float) (point.x + size * 2 / 8), (float) (point.y + size * 1 / 8));
            path.lineTo((float) (point.x + size * 3 / 8), (float) (point.y + size / 2));
            path.lineTo((float) (point.x), (float) (point.y + size * 2 / 8));
            path.lineTo((float) (point.x - size * 3 / 8), (float) (point.y + size / 2));
            path.lineTo((float) (point.x - size * 2 / 8), (float) (point.y + size * 1 / 8));
            path.lineTo((float) (point.x - size / 2), (float) (point.y - size * 1 / 8));
            path.lineTo((float) (point.x - size * 1 / 8), (float) (point.y - size * 1 / 8));
            path.close();
            return new PathShape(path);
        }
    }

    public static class Triangle extends BasePointShapeFactory {
        /**
         * Creates a new factory for points with default size.
         * 
         */
        public Triangle() {
            super();
        }

        /**
         * Creates a factory for points of given size.
         * 
         * @param size
         *          the size of the points
         */
        public Triangle( double size ) {
            super(size);
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public DrawableShape createPoint( PointF point ) {

            Path path = new Path();
            path.moveTo((float) (point.x), (float) (point.y - size / 2));
            path.lineTo((float) (point.x + size / 2), (float) (point.y + size / 2));
            path.lineTo((float) (point.x - size / 2), (float) (point.y + size / 2));
            path.lineTo((float) (point.x), (float) (point.y - size / 2));

            return new PathShape(path);
        }

    }
    public static class Circle extends BasePointShapeFactory {
        /**
         * Creates a new factory for points with default size.
         * 
         */
        public Circle() {
            super();
        }

        /**
         * Creates a factory for points of given size.
         * 
         * @param size
         *          the size of the points
         */
        public Circle( double size ) {
            super(size);
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public DrawableShape createPoint( PointF point ) {
            float x = (float) (point.x - (size / 2));
            float y = (float) (point.y - (size / 2));
            OvalShape shape = new OvalShape(x, y, (float) size, (float) size);
            return shape;
        }

    }
    public static class Cross extends BasePointShapeFactory {
        /**
         * Creates a new factory for points with default size.
         * 
         */
        public Cross() {
            super();
        }

        /**
         * Creates a factory for points of given size.
         * 
         * @param size
         *          the size of the points
         */
        public Cross( double size ) {
            super(size);
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public DrawableShape createPoint( PointF point ) {

            float x1 = (float) (point.x - size / 2f);
            float x2 = (float) (point.x - size / 4f);
            float x3 = (float) (point.x + size / 4f);
            float x4 = (float) (point.x + size / 2f);

            float y1 = (float) (point.y - size / 2f);
            float y2 = (float) (point.y - size / 4f);
            float y3 = (float) (point.y + size / 4f);
            float y4 = (float) (point.y + size / 2f);

            Path path = new Path();
            path.moveTo(x2, y1);
            path.lineTo(x3, y1);
            path.lineTo(x3, y2);
            path.lineTo(x4, y2);
            path.lineTo(x4, y3);
            path.lineTo(x3, y3);
            path.lineTo(x3, y4);
            path.lineTo(x2, y4);
            path.lineTo(x2, y3);
            path.lineTo(x1, y3);
            path.lineTo(x1, y2);
            path.lineTo(x2, y2);
            path.lineTo(x2, y1);

            return new PathShape(path);
        }

    }
    public static class X extends BasePointShapeFactory {
        /**
         * Creates a new factory for points with default size.
         * 
         */
        public X() {
            super();
        }

        /**
         * Creates a factory for points of given size.
         * 
         * @param size
         *          the size of the points
         */
        public X( double size ) {
            super(size);
        }

        /**
         * Creates a shape representing a point.
         * 
         * @param point
         *          the location of the point
         * @return a shape
         */
        public DrawableShape createPoint( PointF point ) {
            Path path = new Path();
            path.moveTo((float) (point.x), (float) (point.y - size * 1 / 8));
            path.lineTo((float) (point.x + size * 2 / 8), (float) (point.y - size / 2));
            path.lineTo((float) (point.x + size / 2), (float) (point.y - size / 2));
            path.lineTo((float) (point.x + size * 1 / 8), (float) (point.y));
            path.lineTo((float) (point.x + size / 2), (float) (point.y + size / 2));
            path.lineTo((float) (point.x + size * 2 / 8), (float) (point.y + size / 2));
            path.lineTo((float) (point.x), (float) (point.y + size * 1 / 8));
            path.lineTo((float) (point.x - size * 2 / 8), (float) (point.y + size / 2));
            path.lineTo((float) (point.x - size / 2), (float) (point.y + size / 2));
            path.lineTo((float) (point.x - size * 1 / 8), (float) (point.y));
            path.lineTo((float) (point.x - size / 2), (float) (point.y - size / 2));
            path.lineTo((float) (point.x - size * 2 / 8), (float) (point.y - size / 2));
            path.close();
            return new PathShape(path);
        }

    }
}
