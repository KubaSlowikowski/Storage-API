package pl.slowikowski.demo.feign_client.soap_client;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import pl.slowikowski.demo.feign_client.LibraryProperties;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryClient;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AuthSoapInterceptor extends AbstractPhaseInterceptor<Message> {

    private final String username;
    private final String password;
    private final AuthLibraryClient authClient;

    //https://cxf.apache.org/docs/interceptors.html
    public AuthSoapInterceptor(final AuthLibraryClient authClient, LibraryProperties libraryProperties) {
        super(Phase.USER_LOGICAL);
        this.authClient = authClient;
        this.username = libraryProperties.getUsername();
        this.password = libraryProperties.getPassword();
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        Map<String, List<String>> protocolHeaders = CastUtils.cast((Map<?, ?>) message.get(Message.PROTOCOL_HEADERS));
        if (protocolHeaders == null) {
            protocolHeaders = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        }
        var authRequest = new AuthLibraryRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        var token = authClient.authenticateUser(authRequest).getToken();
        protocolHeaders.put("Authorization", Collections.singletonList("Bearer " + token));

        message.put(Message.PROTOCOL_HEADERS, protocolHeaders);
    }
}
