package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    BoardTest.class, 
    CoordinateTest.class, 
    GameTest.class,
    PieceTest.class } )
public final class AllModelTest {
}
