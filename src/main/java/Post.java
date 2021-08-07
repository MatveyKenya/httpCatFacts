import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    private final String id;
    private final String text;
    private final Type type;
    private final String user;
    private final Integer upvote;

    public Post(@JsonProperty("id") String id,
                @JsonProperty("text") String text,
                @JsonProperty("type") Type type,
                @JsonProperty("user") String user,
                @JsonProperty("upvotes") Integer upvote) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvote = upvote;
    }

    public Integer getUpvote() {
        return upvote;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", user='" + user + '\'' +
                ", upvote=" + upvote +
                '}';
    }
}
