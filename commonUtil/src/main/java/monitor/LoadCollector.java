package monitor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author zhunhuang, 2021/2/3
 */
public class LoadCollector {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadCollector.class);

    private String redirectPath;
    private ProcessBuilder.Redirect redirect;

    public void run() {
        LOGGER.info("收集系统负载-start");
        List<String> cmds = getCommands();
        for (String cmd : cmds) {
            runProcess(cmd);
        }
        LOGGER.info("收集系统负载-end");
    }

    public LoadCollector(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    @PostConstruct
    public void init() {
        redirect = ProcessBuilder.Redirect.appendTo(new File(redirectPath));
    }

    private List<String> getCommands() {
        String cmds = "iostat,top -bn1";
        if (StringUtils.isEmpty(cmds)) {
            return new ArrayList<>();
        }
        String[] commands = cmds.split(",");
        return new ArrayList<>(Arrays.asList(commands));
    }

    private void runProcess(String cmd) {
        String[] cmdSplits = cmd.split(" ");
        Process process;
        try {
            process = new ProcessBuilder().command(cmdSplits).redirectOutput(redirect).start();
        } catch (IOException e) {
            LOGGER.error("fail to run cmd. param:{}", cmd, e);
            return;
        }
        printStatus(cmd, process);
    }

    private void printStatus(String cmd, Process process) {
        try {
            process.waitFor(200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("fail to waitFor process. param:{}", cmd, e);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        StringBuilder errorBuilder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                errorBuilder.append(line);
                errorBuilder.append("\n");
            }
        } catch (Exception e) {
            LOGGER.error("fail to read stderr. param:{}", cmd, e);
        }
        String error = errorBuilder.toString();
        if (StringUtils.isEmpty(error)) {
            LOGGER.info("{} 执行成功", cmd);
        } else {
            LOGGER.info("{} 执行失败:{}", cmd, error);
        }
    }
}
