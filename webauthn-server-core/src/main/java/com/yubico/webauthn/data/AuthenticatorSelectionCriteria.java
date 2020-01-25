// Copyright (c) 2018, Yubico AB
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.yubico.webauthn.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;


/**
 * This class may be used to specify requirements regarding authenticator attributes.
 *
 * @see <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#dictdef-authenticatorselectioncriteria">§5.4.4.
 * Authenticator Selection Criteria (dictionary AuthenticatorSelectionCriteria)
 * </a>
 */
@Value
@Builder(toBuilder = true)
public class AuthenticatorSelectionCriteria {

    /**
     * If present, eligible authenticators are filtered to only authenticators attached with the specified <a
     * href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#attachment">§5.4.5 Authenticator Attachment Enumeration
     * (enum AuthenticatorAttachment)</a>.
     */
    private final AuthenticatorAttachment authenticatorAttachment;

    /**
     * Describes the Relying Party's requirements regarding resident credentials. If set to <code>true</code>, the
     * authenticator MUST create a <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#client-side-resident-public-key-credential-source">client-side-resident
     * public key credential source</a> when creating a public key credential.
     */
    @Builder.Default
    private final boolean requireResidentKey = false;

    /**
     * Describes the Relying Party's requirements regarding <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#user-verification">user
     * verification</a> for the
     * <code>navigator.credentials.create()</code> operation. Eligible authenticators are filtered to only those
     * capable of satisfying this requirement.
     */
    @NonNull
    @Builder.Default
    private UserVerificationRequirement userVerification = UserVerificationRequirement.PREFERRED;

    /**
     * If present, eligible authenticators are filtered to only authenticators attached with the specified <a
     * href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#attachment">§5.4.5 Authenticator Attachment Enumeration
     * (enum AuthenticatorAttachment)</a>.
     */
    public Optional<AuthenticatorAttachment> getAuthenticatorAttachment() {
        return Optional.ofNullable(authenticatorAttachment);
    }

    @JsonCreator
    private AuthenticatorSelectionCriteria(
        @JsonProperty("authenticatorAttachment") AuthenticatorAttachment authenticatorAttachment,
        @JsonProperty("requireResidentKey") boolean requireResidentKey,
        @NonNull @JsonProperty("userVerification") UserVerificationRequirement userVerification
    ) {
        this.authenticatorAttachment = authenticatorAttachment;
        this.requireResidentKey = requireResidentKey;
        this.userVerification = userVerification;
    }

    public static class AuthenticatorSelectionCriteriaBuilder {
        private AuthenticatorAttachment authenticatorAttachment = null;

        /**
         * If present, eligible authenticators are filtered to only authenticators attached with the specified <a
         * href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#attachment">§5.4.5 Authenticator Attachment Enumeration
         * (enum AuthenticatorAttachment)</a>.
         */
        public AuthenticatorSelectionCriteriaBuilder authenticatorAttachment(@NonNull Optional<AuthenticatorAttachment> authenticatorAttachment) {
            return this.authenticatorAttachment(authenticatorAttachment.orElse(null));
        }

        /**
         * If present, eligible authenticators are filtered to only authenticators attached with the specified <a
         * href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#attachment">§5.4.5 Authenticator Attachment Enumeration
         * (enum AuthenticatorAttachment)</a>.
         */
        public AuthenticatorSelectionCriteriaBuilder authenticatorAttachment(AuthenticatorAttachment authenticatorAttachment) {
            this.authenticatorAttachment = authenticatorAttachment;
            return this;
        }
    }
}
