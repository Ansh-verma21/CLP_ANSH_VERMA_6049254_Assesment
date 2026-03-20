package com.cg.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Track;
import com.cg.repo.TrackRepository;

@RestController
@RequestMapping("/tracks")
public class TrackController {
	@Autowired
	private TrackRepository tr;
	@PostMapping
	public ResponseEntity<String> addTrack(@RequestBody Track track){
		try {
		tr.save(track);
		return new ResponseEntity<String>("Success inserting",HttpStatus.OK);
		
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Not inserted",HttpStatus.BAD_REQUEST);
			
		}
	}
	@GetMapping
	private ResponseEntity<List<Track>> getTracks()
	{
		List<Track> l=tr.findAll();
		if(l.size()>0)
		{
			return new ResponseEntity<List<Track>>(l,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Track>>(l,HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping("/byTitle")
	public ResponseEntity<List<Track>> getTracksByTitle(@RequestParam("title") String title){
		List<Track> tl=tr.findByTitle(title);
		if(tl.size()>0)
		{
			return new ResponseEntity<List<Track>>(tl,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Track>>(tl,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getTrack( int id){
		Optional<Track> to=tr.findById(id);
		Object o;
		if(to.isPresent())
		{
		 o=to.get();
		 return new ResponseEntity<Object>(o,HttpStatus.OK);
		 
		}
		else
		{
			o=null;
			return new ResponseEntity<Object>(o,HttpStatus.NOT_FOUND);
		}
		
	}

}
