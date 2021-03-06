/*
 * Artificial Intelligence for Humans
 * Volume 3: Deep Learning and Neural Networks
 * Java Version
 * http://www.aifh.org
 * http://www.jeffheaton.com
 *
 * Code repository:
 * https://github.com/jeffheaton/aifh
 *
 * Copyright 2014-2015 by Jeff Heaton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information on Heaton Research copyrights, licenses
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package com.heatonresearch.aifh.ann.activation;

import org.encog.Encog;
import org.encog.mathutil.BoundMath;

/**
 * The softmax activation function. This activation function is usually used on the output layer of a
 * classification network.
 */
public class ActivationSoftMax implements ActivationFunction {


	/**
	 * The parameters.
	 */
	private final double[] params;

	/**
	 * Construct the soft-max activation function.
	 */
	public ActivationSoftMax() {
		this.params = new double[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void activationFunction(final double[] x, final int start,
			final int size) {
		double sum = 0;
		for (int i = start; i < start + size; i++) {
			x[i] = BoundMath.exp(x[i]);
			sum += x[i];
		}
		if(Double.isNaN(sum) || sum <Encog.DEFAULT_DOUBLE_EQUAL ) {
			for (int i = start; i < start + size; i++) {
				x[i] = 1.0/size;
			}
		} else {
			for (int i = start; i < start + size; i++) {
				x[i] = x[i] / sum;
			}
		}
	}

	/**
	 * @return The object cloned;
	 */
	@Override
	public final ActivationFunction clone() {
		return new ActivationSoftMax();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double derivativeFunction(final double b, final double a) {
		return a * (1.0 - a);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String[] getParamNames() {
		final String[] result = {};
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double[] getParams() {
		return this.params;
	}

	/**
	 * @return Return false, softmax has no derivative.
	 */
	@Override
	public final boolean hasDerivative() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setParam(final int index, final double value) {
		this.params[index] = value;
	}
}
