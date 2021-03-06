package tikape.keskustelufoorumi.validator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarno
 */
public class Validator<K> {
    private List<IRule> rules = new ArrayList();
    private String reason;
    
    public Validator() {
        
    }
        
    public String getReason() {
        return this.reason;
    }
    
    public void addRule(IRule<K> rule) {
        this.rules.add(rule);
    }
    
    public Boolean validate(K data) {
        for(int i = 0; i < this.rules.size(); i++) {
            IRule rule = this.rules.get(i);
            
            if(rule.validate(data) == false) {
                this.reason = rule.getReason();
                return false;
            }
        }
        
        return true;
    }
}
