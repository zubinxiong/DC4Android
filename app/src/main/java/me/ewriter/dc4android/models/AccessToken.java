package me.ewriter.dc4android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zubin on 16/6/11.
 */
public class AccessToken {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("example_parameter")
    @Expose
    private String exampleParameter;

    /**
     * @return The accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken The access_token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return The tokenType
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * @param tokenType The token_type
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * @return The expiresIn
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn The expires_in
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return The refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken The refresh_token
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return The exampleParameter
     */
    public String getExampleParameter() {
        return exampleParameter;
    }

    /**
     * @param exampleParameter The example_parameter
     */
    public void setExampleParameter(String exampleParameter) {
        this.exampleParameter = exampleParameter;
    }
}
