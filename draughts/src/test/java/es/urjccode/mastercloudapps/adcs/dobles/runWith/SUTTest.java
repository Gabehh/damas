package es.urjccode.mastercloudapps.adcs.dobles.runWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SUTTest {

    @Mock
    DOC doc;

    @Test
    public void testM1(){
        // when/thenReturn
        when(doc.exerciseDOC(true)).thenReturn(-1);
        SUT sut = new SUT(doc);
        assertEquals(-1, sut.exerciseSUT(true));
    }

    @Test(expected = Exception.class)
    public void testM2(){
        // when/thenThrow
        when(doc.exerciseDOC(false)).thenThrow(Exception.class);
        SUT sut = new SUT(doc);
        assertEquals(-1, sut.exerciseSUT(false));
    }

    @Test
    public void testM3(){
        // cualquier valor de un tipo: any<Tipo>
        when(doc.exerciseDOC(anyBoolean())).thenReturn(666).thenReturn(333);
        SUT sut = new SUT(doc);
        assertEquals(666, sut.exerciseSUT(false));
        assertEquals(333, sut.exerciseSUT(false));
    }

    @Test
    public void testM4(){
        // doReturn/when
        doReturn(0).when(doc).exerciseDOC(true);
        SUT sut = new SUT(doc);
        assertEquals(0, sut.exerciseSUT(true));
    }

    @Test(expected = Exception.class)
    public void testM5(){
        // doThrow/when
        doThrow(Exception.class).when(doc).exerciseDOC(true);
        SUT sut = new SUT(doc);
        assertEquals(0, sut.exerciseSUT(true));
    }

    @Test
    public void testM6(){
        // verify
        when(doc.exerciseDOC(true)).thenReturn(7);
        SUT sut = new SUT(doc);
        sut.exerciseSUT(true);
        verify(doc).exerciseDOC(true);
    }

    @Test
    public void testM7(){
        // verify con cualquier tipo
        when(doc.exerciseDOC(true)).thenReturn(7);
        SUT sut = new SUT(doc);
        sut.exerciseSUT(true);
        verify(doc).exerciseDOC(anyBoolean());
    }

    @Test
    public void testM8(){
        // verify varias veces
        when(doc.exerciseDOC(true)).thenReturn(7);
        SUT sut = new SUT(doc);
        sut.exerciseSUT(true);
        sut.exerciseSUT(true);
        verify(doc, times(2)).exerciseDOC(anyBoolean());
    }

}