package test.utilities;

public class TTransition {
	
	private String target;
	private String source;
	private String preCondition;
	private String postCondition;
	private String operation;
	
	public TTransition(String target, String source) {
		this.target = target;
		this.source = source;
	}
	
	public TTransition(String target, String source, String preCondition, String postCondition, String operation) {
		super();
		this.target = target;
		this.source = source;
		this.preCondition = preCondition;
		this.postCondition = postCondition;
		this.operation = operation;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public void setPostCondition(String postCondition) {
		this.postCondition = postCondition;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((postCondition == null) ? 0 : postCondition.hashCode());
		result = prime * result + ((preCondition == null) ? 0 : preCondition.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TTransition other = (TTransition) obj;
		if (operation == null) {
			if (other.operation != null && !other.operation.contains("unnamed") && !other.operation.trim().isEmpty())
				return false;
		} else if (!operation.equals(other.operation)
				&& !(operation.trim().isEmpty() && other.operation == null)
				&& !(operation.trim().isEmpty() && other.operation.contains("unnamed"))
				&& !((other.operation == null || other.operation.trim().isEmpty()) && operation.contains("unnamed"))
				&& !((other.operation != null && operation.contains(other.operation) && operation.length() == other.operation.length()+1))
				&& !((other.operation != null && other.operation.contains(operation) && other.operation.length() == operation.length()+1)))
			return false;
		if (postCondition == null) {
			if (other.postCondition != null && !other.postCondition.trim().isEmpty() && !other.postCondition.equals("true") && !other.postCondition.equals("USE"))
				return false;
		} else if (!postCondition.equals(other.postCondition)
				&& !((other.postCondition == null || other.postCondition.trim().isEmpty()) && postCondition.equals("true"))
				&& !(postCondition.trim().isEmpty() && other.postCondition != null && other.postCondition.equals("true"))
				&& !(other.postCondition == null && postCondition.trim().isEmpty())
				&& !(other.postCondition != null && other.postCondition.equals("USE")) && !postCondition.equals("USE")
				&& !(other.postCondition != null && other.postCondition.trim().replaceAll(" ", "") != null && postCondition.equals(other.postCondition.trim().replaceAll(" ", ""))))
			return false;
		if (preCondition == null) {
			if (other.preCondition != null && !other.preCondition.trim().isEmpty() && !other.preCondition.equals("true") && !other.preCondition.equals("USE"))
				return false;
		} else if (!preCondition.equals(other.preCondition)
				&& !((other.preCondition == null || other.preCondition.trim().isEmpty()) && preCondition.equals("true"))
				&& !(preCondition.trim().isEmpty() && other.preCondition != null && other.preCondition.equals("true"))
				&& !(other.preCondition == null && preCondition.trim().isEmpty())
				&& !(other.preCondition != null && other.preCondition.equals("USE")) && !preCondition.equals("USE")
				&& !(other.preCondition != null && other.preCondition.trim().replaceAll(" ", "") != null && preCondition.equals(other.preCondition.trim().replaceAll(" ", ""))))
			return false;
		if (source == null) {
			if (other.source != null && !other.source.contains("unnamed") && !other.source.trim().isEmpty())
				return false;
		} else if (!source.equals(other.source)
				&& !(source.trim().isEmpty() && other.source == null)
				&& !(source.trim().isEmpty() && other.source.contains("unnamed"))
				&& !((other.source == null || other.source.trim().isEmpty()) && source.contains("unnamed"))
				&& !((other.source != null && source.contains(other.source) && source.length() == other.source.length()+1))
				&& !((other.source != null && other.source.contains(source) && other.source.length() == source.length()+1)))
			return false;
		if (target == null) {
			if (other.target != null && !other.target.contains("unnamed") && !other.target.trim().isEmpty())
				return false;
		} else if (!target.equals(other.target)
				&& !(target.trim().isEmpty() && other.target == null)
				&& !(target.trim().isEmpty() && other.target.contains("unnamed"))
				&& !((other.target == null || other.target.trim().isEmpty()) && target.contains("unnamed"))
				&& !((other.target != null && target.contains(other.target) && target.length() == other.target.length()+1))
				&& !((other.target != null && other.target.contains(target) && other.target.length() == target.length()+1)))
			return false;
		return true;
	}

}
