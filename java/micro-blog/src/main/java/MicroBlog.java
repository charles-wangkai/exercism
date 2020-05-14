class MicroBlog {
    public String truncate(String input) {
        return input.substring(0, input.offsetByCodePoints(0, Math.min(5, input.codePointCount(0, input.length()))));
    }
}
