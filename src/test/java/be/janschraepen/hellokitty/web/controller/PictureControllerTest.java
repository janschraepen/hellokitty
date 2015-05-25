package be.janschraepen.hellokitty.web.controller;

import be.janschraepen.hellokitty.domain.cat.CatPictureDTO;
import be.janschraepen.hellokitty.services.CatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PictureControllerTest {

    public static final String UUID = "uuid";

    @Mock
    private CatService catService;

    @InjectMocks
    private PictureController underTest = new PictureController();

    @Test
    public void testGetBytesAsInputStream() throws Exception {
        String aString = "test";
        InputStream is = underTest.getBytesAsInputStream(aString.getBytes());
        assertNotNull(is);
        assertTrue(is instanceof InputStream);
    }

    @Test
    public void testRenderCatPicture_noPictureFound() throws Exception {
        when(catService.findCatPicture(UUID)).thenReturn(null);

        ResponseEntity<InputStreamResource> result = underTest.renderCatPicture("uuid");
        assertNull(result);
    }

    @Test
    public void testRenderCatPicture_pictureFound() throws Exception {
        CatPictureDTO catPicture = new CatPictureDTO();
        catPicture.setCatId("uuid");
        catPicture.setPicture(new byte[] { });
        catPicture.setSize(20L);
        catPicture.setContentType("image/jpeg");
        when(catService.findCatPicture(UUID)).thenReturn(catPicture);

        ResponseEntity<InputStreamResource> result = underTest.renderCatPicture("uuid");
        assertNotNull(result);
    }

}