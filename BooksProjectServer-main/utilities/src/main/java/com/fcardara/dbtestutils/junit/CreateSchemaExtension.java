package com.fcardara.dbtestutils.junit;

import java.nio.file.Path;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.fcardara.dbtestutils.db.ScriptExecutor;

public class CreateSchemaExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
    private static final Lock lock = new ReentrantLock();
    private static volatile boolean started = false;


    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        lock.lock();
        try {
            if (!started) {
                ScriptExecutor.execute(Path.of("schema.sql"));
                ScriptExecutor.execute(Path.of("data.sql"));
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void close() throws Throwable {
        // entityManagerFactory.close();
    }
}
