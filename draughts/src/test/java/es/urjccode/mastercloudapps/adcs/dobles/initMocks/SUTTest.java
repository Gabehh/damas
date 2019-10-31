package es.urjccode.mastercloudapps.adcs.dobles.initMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SUTTest {

    @Mock
    DOC doc;
    
    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testM(){
        when(doc.exerciseDOC(true)).thenReturn(-1);
        SUT sut = new SUT(doc);
        assertEquals(-1, sut.exerciseSUT(true));
        verify(doc).exerciseDOC(true);
    }

}