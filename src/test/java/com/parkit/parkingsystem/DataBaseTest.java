package com.parkit.parkingsystem;

import com.parkit.parkingsystem.config.DataBaseConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataBaseTest {

    @Mock
    private static DataBaseConfig dataBaseConfig;

    @Test
    public void closeNullPreparedStatement(){
        PreparedStatement ps = null;
        assertThrows(NullPointerException.class, () -> dataBaseConfig.closePreparedStatement(ps));
    }

    @Test
    public void closeNullResultSet(){
        ResultSet rs = null;
        assertThrows(NullPointerException.class, () -> dataBaseConfig.closeResultSet(rs));
    }
}
