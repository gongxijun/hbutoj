package com.hbut.admin.action;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Config;
import com.util.StreamHandler;

import java.io.File;
import java.util.List;

public class TestFileListAction extends ActionSupport {
    private Integer problemId;
    private List<File> inputFiles;
    private List<File> outputFiles;
    private Integer nCount = 0;

    private List<String> inputString;
    private List<String> outputString;

    public List<String> getInputString() {
        return inputString;
    }

    public void setInputString(List<String> inputString) {
        this.inputString = inputString;
    }

    public List<String> getOutputString() {
        return outputString;
    }

    public void setOutputString(List<String> outputString) {
        this.outputString = outputString;
    }

    public Integer getnCount() {
        return nCount;
    }

    public void setnCount(Integer nCount) {
        this.nCount = nCount;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public List<File> getOutputFiles() {
        return outputFiles;
    }

    public void setOutputFiles(List<File> outputFiles) {
        this.outputFiles = outputFiles;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String execute() {
        try {

            inputFiles = Lists.newArrayList();
            // inputFiles = StreamHandler.getInputFiles(problemId);
            outputFiles = Lists.newArrayList();
            // outputFiles = StreamHandler.getOutputFiles(problemId);

            inputString = Lists.newArrayList();
            outputString = Lists.newArrayList();

            Integer index = new Integer(1);

            for (index = 1; ; index++) {

                String fpath = Config.getValue("OJ_DATA_PATH")
                        + problemId.toString() + "\\data" + index + ".in";
                File file = new File(fpath);
                if (file.exists()) {
                    inputFiles.add(file);
                    String content_ = StreamHandler.read(file)
                            .replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                            .replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
                    // StreamHandler.getInputFiles(problemId).
                    inputString.add(content_);
                } else {
                    break;
                }

                fpath = Config.getValue("OJ_DATA_PATH") + problemId.toString()
                        + "\\data" + index + ".out";
                file = new File(fpath);

                if (file.exists()) {
                    outputFiles.add(file);
                    String content_ = StreamHandler.read(file)
                            .replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                            .replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
                    // StreamHandler.getInputFiles(problemId).
                    outputString.add(content_);
                } else {
                    inputString.remove(index - 1);
                    inputFiles.remove(index - 1);
                    break;
                }
            }

        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }
}
