package com.revature.erm.dtos.requests;

public class ListUserReimbursementsRequest {

    private String author_id;

    public String getAuthorId() {
        return author_id;
    }

    public void setAuthorId(String authorId) {
        this.author_id = authorId;
    }

    public ListUserReimbursementsRequest(){ super(); }

    @Override
    public String toString() {
        return "ListUserReimbursementsRequest{" +
                "author_id='" + author_id + '}';
    }
}
