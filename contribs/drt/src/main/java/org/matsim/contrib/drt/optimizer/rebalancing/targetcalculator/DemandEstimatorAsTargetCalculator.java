/*
 * *********************************************************************** *
 * project: org.matsim.*
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2020 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** *
 */

package org.matsim.contrib.drt.optimizer.rebalancing.targetcalculator;

import org.matsim.contrib.common.zones.Zone;
import org.matsim.contrib.drt.optimizer.rebalancing.demandestimator.ZonalDemandEstimator;
import org.matsim.contrib.dvrp.fleet.DvrpVehicle;

import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;

/**
 * @author michalm
 */
public class DemandEstimatorAsTargetCalculator implements RebalancingTargetCalculator {
	private final ZonalDemandEstimator demandEstimator;
	private final double demandEstimationPeriod;

	public DemandEstimatorAsTargetCalculator(ZonalDemandEstimator demandEstimator, double demandEstimationPeriod) {
		this.demandEstimator = demandEstimator;
		this.demandEstimationPeriod = demandEstimationPeriod;
	}

	@Override
	public ToDoubleFunction<Zone> calculate(double time,
											Map<Zone, List<DvrpVehicle>> rebalancableVehiclesPerZone) {
		return demandEstimator.getExpectedDemand(time, demandEstimationPeriod);
	}
}
