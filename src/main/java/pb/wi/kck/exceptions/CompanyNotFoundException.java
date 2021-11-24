package pb.wi.kck.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Integer companyId) {
        super("Could not find company " + companyId);
    }
}
