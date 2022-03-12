package com.rajch.browserstack;

/*
* Problem Statement: https://practice.geeksforgeeks.org/problems/comment-removal2017/1
* */
public class RemoveCommits {
    public String removeComments(String code)
    {
        return code.replaceAll("(//.*\\\\n)|(/\\*.*\\*/)", "");
    }
}
