package com.itunes.music.models;

public class ItunesMusic
{
    private Results[] results;

    private String resultCount;

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    public String getResultCount ()
    {
        return resultCount;
    }

    public void setResultCount (String resultCount)
    {
        this.resultCount = resultCount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", resultCount = "+resultCount+"]";
    }
}
