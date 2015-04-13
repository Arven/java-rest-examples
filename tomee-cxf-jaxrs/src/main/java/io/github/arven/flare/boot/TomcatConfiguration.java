/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.arven.flare.boot;

import org.apache.openejb.util.NetworkUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
* @version $Rev$ $Date$
*/
public class TomcatConfiguration {

    private int httpPort = 8080;
    private int stopPort = 8005;
    private String host = "localhost";
    protected String dir;
    private File serverXml;
    private boolean keepServerXmlAsThis;
    private Properties properties;
    private boolean quickSession = true;
    private boolean skipHttp;

    private int httpsPort = 8443;
    private boolean ssl;
    private String keystoreFile;
    private String keystorePass;
    private String keystoreType = "JKS";
    private String clientAuth;
    private String keyAlias;
    private String sslProtocol;

    private boolean deployOpenEjbApp;

    private Map<String, String> users;
    private Map<String, String> roles;
    private Map<String, String> filters;

    /**
     * when needed temp file only (deployClasspathAsWebapp() for instance)
     */
    private String tempDir = new File(System.getProperty("java.io.tmpdir"), "tomee-embedded_" + System.currentTimeMillis()).getAbsolutePath();

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(final int httpPort) {
        this.httpPort = httpPort;
    }

    public TomcatConfiguration randomHttpPort() {
        this.httpPort = NetworkUtil.getNextAvailablePort();
        return this;
    }

    public int getStopPort() {
        return stopPort;
    }

    public void setStopPort(final int stopPort) {
        this.stopPort = stopPort;
    }

    public String getDir() {
        return dir;
    }

    public TomcatConfiguration dir(final String dir) {
        setDir(dir);
        return this;
    }

    public void setDir(final String dir) {
        this.dir = dir;
    }

    public String getHost() {
        return host;
    }

    public int getHttpsPort() {
        return httpsPort;
    }

    public void setHttpsPort(final int httpsPort) {
        this.httpsPort = httpsPort;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getKeystoreFile() {
        return keystoreFile;
    }

    public void setKeystoreFile(final String keystoreFile) {
        this.keystoreFile = keystoreFile;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    public void setKeystorePass(final String keystorePass) {
        this.keystorePass = keystorePass;
    }

    public String getKeystoreType() {
        return keystoreType;
    }

    public void setKeystoreType(final String keystoreType) {
        this.keystoreType = keystoreType;
    }

    public String getClientAuth() {
        return clientAuth;
    }

    public void setClientAuth(final String clientAuth) {
        this.clientAuth = clientAuth;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(final String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public void setServerXml(final String file) {
        if (file == null) {
            serverXml = null;
        } else {
            final File sXml = new File(file);
            if (sXml.exists()) {
                serverXml = sXml;
            }
        }
    }

    public File getServerXmlFile() {
        return serverXml;
    }

    public boolean hasServerXml() {
        return serverXml != null && serverXml.exists();
    }

    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public boolean isQuickSession() {
        return quickSession;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(final boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isSkipHttp() {
        return skipHttp;
    }

    public void setSkipHttp(final boolean skipHttp) {
        this.skipHttp = skipHttp;
    }

    public void setQuickSession(final boolean quickSession) {
        this.quickSession = quickSession;
    }

    public String getSslProtocol() {
        return sslProtocol;
    }

    public void setSslProtocol(final String sslProtocol) {
        this.sslProtocol = sslProtocol;
    }

    public TomcatConfiguration property(final String key, final String value) {
        if (properties == null) {
            properties = new Properties();
        }
        properties.setProperty(key, value);
        return this;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(final String tempDir) {
        this.tempDir = tempDir;
    }

    public boolean isDeployOpenEjbApp() {
        return deployOpenEjbApp;
    }

    public void setDeployOpenEjbApp(final boolean deployOpenEjbApp) {
        this.deployOpenEjbApp = deployOpenEjbApp;
    }

    public TomcatConfiguration http(final int port) {
        setHttpPort(port);
        return this;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(final Map<String, String> users) { // useful for tools like maven plugin
        this.users = users;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(final Map<String, String> roles) {
        this.roles = roles;
    }

    public boolean isKeepServerXmlAsThis() {
        return keepServerXmlAsThis;
    }

    public void setKeepServerXmlAsThis(final boolean keepServerXmlAsThis) {
        this.keepServerXmlAsThis = keepServerXmlAsThis;
    }

    public TomcatConfiguration user(final String name, final String pwd) {
        if (users == null) {
            users = new HashMap<String, String>();
        }
        this.users.put(name, pwd);
        return this;
    }

    public TomcatConfiguration role(final String user, final String roles) {
        if (this.roles == null) {
            this.roles = new HashMap<String, String>();
        }
        this.roles.put(user, roles);
        return this;
    }
    
    public TomcatConfiguration filter(final String name, final String clazz) {
        if (this.filters == null) {
            this.filters = new HashMap<String, String>();
        }
        this.filters.put(name, clazz);
        return this;
    }
    
    public Map<String, String> getFilters() {
        return this.filters;
    }
}
