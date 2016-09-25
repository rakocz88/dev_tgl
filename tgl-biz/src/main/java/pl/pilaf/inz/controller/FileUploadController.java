package pl.pilaf.inz.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.Song;
import pl.pilaf.inz.repository.AlbumRepository;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.SongRepository;

@RestController
@RequestMapping(value = "/file")
public class FileUploadController {

	private SongRepository songRepository;

	private BandRepository bandRepository;
	
	private AlbumRepository albumRepository;
	
	
	@Autowired
	public FileUploadController(SongRepository songRepository, BandRepository bandRepository,
			AlbumRepository albumRepository) {
		super();
		this.songRepository = songRepository;
		this.bandRepository = bandRepository;
		this.albumRepository = albumRepository;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void UploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {

		// Attachment attachment = new Attachment();
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		// attachment.setName(fileName);
		// attachmentRepository.save(attachment);
		File dir = new File("C:\\GregorianLegend\\backend\\files\\songs\\filipos");
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		;
		File serverFile = new File(dir, fileName);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(file.getBytes());
		stream.close();

	}

	@RequestMapping(value = "{bandId}/upload/{albumId}/{songName}/{songDescription}", method = RequestMethod.POST)
	public void UploadSong(MultipartHttpServletRequest request, HttpServletResponse response,
			@PathVariable("bandId") long bandId, @PathVariable("albumId") long albumId,
			@PathVariable("songName") String songName, @PathVariable("songDescription") String songDescription)
					throws IOException {

		Band band = bandRepository.findOne(bandId);
		Album album = albumRepository.findOne(albumId);

		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		String folderPath = String.format("C:\\inzynierski\\filesStorage\\%s\\%s", band.getName(), album.getName());
		File dir = new File(folderPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File serverFile = new File(dir, fileName);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(file.getBytes());
		String songPath = folderPath.concat("\\").concat(fileName);
		Song song = new Song(songName, songPath, band, album, songDescription);
		songRepository.save(song);
		stream.close();
	}
	
	 @RequestMapping(value = "download/{songId}", method = RequestMethod.GET)
	    public void download(@PathVariable ("songId") Long songId, final HttpServletRequest request, final HttpServletResponse response) {
		 	
		 	Song song = songRepository.findOne(songId);
		 	
	        File file = new File (song.getPath());
	        try (InputStream fileInputStream = new FileInputStream(file);
	                OutputStream output = response.getOutputStream();) {

	            response.reset();

	            response.setContentType("application/octet-stream");
	            response.setContentLength((int) (file.length()));

	            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

	            IOUtils.copyLarge(fileInputStream, output);
	            output.flush();
	        } catch (IOException e) {
	           e.printStackTrace();
	        }

	    }


}