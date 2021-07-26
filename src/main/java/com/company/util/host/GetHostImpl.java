package com.company.util.host;
import com.company.util.host.GetHost;

import static com.company.util.constants.ServerApi.LOCAL_HOST;
import static com.company.util.constants.ServerApi.SERVER_HOST;

public class GetHostImpl implements GetHost {

    String local = LOCAL_HOST;
    String server = SERVER_HOST;

    @Override
    public String getHost() {
        return local;
    }

}
