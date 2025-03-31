package lab.requests.backing;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.html.HtmlDataTable;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lab.requests.data.RequestRepository;
import lab.requests.entities.Request;

import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class RequestsList {
    @Inject
    private RequestRepository requestRepository;
    private HtmlDataTable requestsDataTable;

    //TODO: disable validation on delete
    @Size(min = 3, max = 60, message = "{request.size}")
    private String newRequest;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public String getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }

    public HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    public void setRequestsDataTable(HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }

    @Transactional
    public String addRequest() {
        Request req = new Request();
        req.setRequestDate(LocalDate.now());
        req.setRequestText(newRequest);
        requestRepository.create(req);
        return "requestsList?faces-redirect=true";
    }

    @Transactional
    public String deleteRequest() {
        Request req = (Request)getRequestsDataTable().getRowData();
        requestRepository.remove(req);
        return "requestsList?faces-redirect=true";
    }
}
