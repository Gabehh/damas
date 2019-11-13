package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    CoordinateTest.class, 
    PieceTest.class,
    GameWithDraughtsTest.class,
    GameTest.class, } )
public final class AllModelTest {
}
