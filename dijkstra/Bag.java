package dijkstra;

class Bag {
	private String bagNumber;
	private String entryPoint;
	private String flightId;

	public String getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Bag(String bagNumber, String entryPoint, String flightId) {
		super();
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bagNumber == null) ? 0 : bagNumber.hashCode());
		result = prime * result + ((entryPoint == null) ? 0 : entryPoint.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
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
		Bag other = (Bag) obj;
		if (bagNumber == null) {
			if (other.bagNumber != null)
				return false;
		} else if (!bagNumber.equals(other.bagNumber))
			return false;
		if (entryPoint == null) {
			if (other.entryPoint != null)
				return false;
		} else if (!entryPoint.equals(other.entryPoint))
			return false;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[bagNumber=" + bagNumber + ", entryPoint=" + entryPoint + ", flightId=" + flightId + "]";
	}

}
