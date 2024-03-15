package com.travel.main.placeSave;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceSaveService {

	private final PlaceSaveMapper placesaveMapper;
	
	public void insertSave(Draftviews placesave) {
		this.placesaveMapper.insertSave(placesave);
	}
	
	public List<Draftviews> selectSave() {
		return this.placesaveMapper.getAllSave();
	}
	
	public void deleteSave(int id) {
		Draftviews placesave = new Draftviews();
		placesave.setId(id);
		this.placesaveMapper.delSave(placesave);
	}
}
