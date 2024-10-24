package CodersWomen.studySmart.core.verifications.concretes;

import CodersWomen.studySmart.core.verifications.abstracts.EmailVerificationService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.regex.Pattern;

public class EmailVerification  implements EmailVerificationService {

    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{3})?$";
    @Override
    public boolean isVerified( @RequestBody String eMail) {

        System.out.println(eMail);
        if(!checkEmailFormat(eMail)) {
            return true;
        }
        return false;

    }

    @Override
    public boolean checkEmailFormat( @RequestBody String eMail) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
        return pattern.matcher(eMail).find();

    }
}
