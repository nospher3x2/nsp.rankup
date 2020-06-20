package com.nospher.rankup.database.runnable;

import com.nospher.rankup.database.manager.MySQLManager;

/**
 * @author oNospher
 **/
public class MySQLRefreshRunnable implements Runnable {

    @Override
    public void run() {
        new MySQLManager().refresh();
    }
}
