package com.travel.main.placeSave;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlaceSaveMapper {
	public abstract void insertSave(Draftviews save);
	public abstract List<Draftviews> getAllSave();
	public abstract void delSave(Draftviews save);
	public abstract List<Draftviews> searchTestName();
}
