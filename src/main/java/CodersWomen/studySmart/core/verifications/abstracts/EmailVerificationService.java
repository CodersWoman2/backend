package CodersWomen.studySmart.core.verifications.abstracts;

public interface EmailVerificationService {
    boolean isVerified(String email);
    boolean checkEmailFormat(String email);
}