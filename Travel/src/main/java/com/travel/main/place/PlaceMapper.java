package com.travel.main.place;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlaceMapper {
	public abstract void insertTest(Place mol);
	public abstract List<Place> getAllTest();
	public abstract void delPlace(int id);
	public abstract void delAll();
	public abstract void delPlaceWithId(int id);
	public abstract List<Place> searchTestName(@Param("placename") String placename);
	public abstract List<Place> searchTestCategory(@Param("category") String category);
	public abstract List<Place> getTestByPage(@Param("offset") int offset, @Param("limit") int limit);
}
