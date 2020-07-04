package com.nospher.rankup.database.mysql.runnable;

import com.nospher.rankup.database.mysql.manager.MySQLManager;

/**
 * @author oNospher
 **/
public class MySQLRefreshRunnable implements Runnable {

    @Override
    public void run() {
        new MySQLManager().refresh();
    }
}
