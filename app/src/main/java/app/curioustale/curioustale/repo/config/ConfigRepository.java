package app.curioustale.curioustale.repo.config;

import app.curioustale.curioustale.models.ServerConfig;

public interface ConfigRepository {
    void setTimestamp(ServerTimestampSetListener listener);

    void getServerConfig(ServerConfigResultListener listener);

    interface ServerConfigResultListener {
        void onServerConfigResult(ServerConfig serverConfig);
    }

    interface ServerTimestampSetListener {
        void onTimestampSet();
    }
}
