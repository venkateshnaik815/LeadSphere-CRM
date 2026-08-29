package com.leadsphere.crm.patterns;

import com.iluwatar.observer.WeatherType;

public interface Race extends Observer<GenWeather, Race, WeatherType> {}
