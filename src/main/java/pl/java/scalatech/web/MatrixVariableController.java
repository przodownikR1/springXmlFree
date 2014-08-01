package pl.java.scalatech.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/matrix")
public class MatrixVariableController {

    private static final Logger logger = LoggerFactory.getLogger(MatrixVariableController.class);

    /**
     * Deal with a URL that looks something like this:
     * http://<hostname>:<port>/spring_3_2/matrixvars/stocks;<stock info>
     * For example:
     * http://localhost:8080/spring_3_2/matrixvars/stocks;BT.A=276.70,+10.40,+
     * 3.91;AZN=236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07
     */
    @RequestMapping(value = "/{stocks}", method = RequestMethod.GET)
    @ResponseBody
    public String showPortfolioValues(@MatrixVariable Map<String, List<String>> matrixVars) {

        logger.info("size : {}  values: {}", new Object[] { matrixVars.size(), matrixVars });

        return "ok";
    }

    /**
     * Deal with a URL that looks something like this:
     * http://<hostname>:<port>/spring_3_2/matrixvars/stocks;<stock
     * info>/account<account info>
     * For example:
     * http://localhost:8080/spring_3_2/matrixvars/stocks;BT.A=276.70,,+3.91;AZN
     * =236.00,+103.00;SBRY=375.50/account;name=roger;number=105;location=stoke-
     * on-trent
     */
    @RequestMapping(value = "/{stocks}/{account}", method = RequestMethod.GET)
    @ResponseBody
    public String showPortfolioValuesWithAccountInfo(@MatrixVariable(pathVar = "stocks") Map<String, List<String>> stocks,
            @MatrixVariable(pathVar = "account") Map<String, List<String>> accounts) {
        logger.info("Stocks Values which are: {}", new Object[] { stocks.size(), stocks });

        logger.info("Accounts Values which are: {}", new Object[] { accounts.size(), accounts });

        return "ok";
    }

    // /o/m/42;p=4;q=2
    @RequestMapping(value = "m/{id}", produces = "text/plain")
    public String execute(@PathVariable Integer id, @MatrixVariable Optional<Integer> p, @MatrixVariable Optional<Integer> q) {

        StringBuilder result = new StringBuilder();
        result.append("p: ");
        p.ifPresent(value -> result.append(value));
        result.append(", q: ");
        q.ifPresent(value -> result.append(value));

        return result.toString();
    }
}
