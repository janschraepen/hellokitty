package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatPictureDTO;
import be.janschraepen.hellokitty.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * PictureController class. Used for mapping request
 * render Pictures of Cat, etc., ..
 */
@Controller
public class PictureController {

    @Autowired
    private CatService catService;

    @RequestMapping(value = "/picture/cat/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> renderCatPicture(@PathVariable String uuid) {
        CatPictureDTO catPicture = catService.findCatPicture(uuid);
        if (catPicture == null) {
            return null;
        } else {
            return ResponseEntity.ok()
                    .contentLength(catPicture.getSize())
                    .contentType(MediaType.valueOf(catPicture.getContentType()))
                    .body(new InputStreamResource(getBytesAsInputStream(catPicture.getPicture())));
        }
    }

    /**
     * Bytes as InputStream, using a ByteArrayInputStream.
     *
     * @param bytes the bytes
     * @return InputStream the bytes as InputStream
     */
    InputStream getBytesAsInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

}
