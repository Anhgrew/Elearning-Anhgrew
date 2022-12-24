package com.myclass.service;

import java.util.List;

import com.myclass.entity.Target;

public interface TargetService extends GenericService<Target, Integer> {
	List<Target> findByCourseId(int id);
}
