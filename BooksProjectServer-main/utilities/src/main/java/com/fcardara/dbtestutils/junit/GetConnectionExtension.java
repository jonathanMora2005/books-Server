package com.fcardara.dbtestutils.junit;

import java.nio.file.Path;
import java.sql.Connection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.fcardara.dbtestutils.db.DbUtils;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class GetConnectionExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource, ParameterResolver {
    private static final Lock lock = new ReentrantLock();
    private static volatile boolean started = false;
    private static Connection connection;

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        lock.lock();
        try {
            if (!started) {
                connection = DbUtils.connectToDb();

                context.getRoot().getStore(GLOBAL).put("connection", connection);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void close() throws Throwable {
        connection.close();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
                    return parameterContext.getParameter().getType()
                      .equals(Connection.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
                return extensionContext.getRoot().getStore(GLOBAL).get("connection");
    }
}
